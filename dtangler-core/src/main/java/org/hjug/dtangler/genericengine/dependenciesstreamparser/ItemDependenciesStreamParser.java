// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.genericengine.dependenciesstreamparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hjug.dtangler.core.exception.DtException;
import org.hjug.dtangler.genericengine.types.Item;
import org.hjug.dtangler.genericengine.types.ValidScopes;

/**
 * Parser for the dependency definitions stream.
 * 
 * Dtangler universal frontend API syntax:
 * 
 * dependencies : (dependencyDefinition | itemDefinition)* dependencyDefinition
 * : dependant+ ':' dependee+ '\n' itemDefinition : dependable dependant :
 * dependable dependee : dependable dependaple : displayname | scope '{'
 * fullyqualifiedname '}' fullyqualifiedname : parentfqn? displayname parentfqn
 * : fullyqualifiedname displayname : string
 * 
 */
public class ItemDependenciesStreamParser {

	public Set<Item> parse(ValidScopes validScopes, File dependencyFile,
			String encoding) {
		Set<Item> items = new HashSet<>();
		int lineNo = 0;
		try (BufferedReader in =  getBufferedIOReader(dependencyFile)) {
			lineNo = parseLines(validScopes, encoding, items, lineNo, in);
		} catch (IOException e) {
			throw new DtException("error in reading the item dependencies: "
					+ e.getMessage());
		} catch (DtException e) {
			throw new DtException(
					"error in parsing the item dependencies in line (" + lineNo
							+ "): " + e.getMessage());
		}

		return items;
	}

	public Set<Item> parse(ValidScopes validScopes, String encoding) {
		Set<Item> items = new HashSet<>();
		int lineNo = 0;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
			lineNo = parseLines(validScopes, encoding, items, lineNo, in);
		} catch (IOException e) {
			throw new DtException("error in reading the item dependencies: "
					+ e.getMessage());
		} catch (DtException e) {
			throw new DtException(
					"error in parsing the item dependencies in line (" + lineNo
							+ "): " + e.getMessage());
		}

		return items;
	}

	private int parseLines(ValidScopes validScopes, String encoding, Set<Item> items, int lineNo, BufferedReader in) throws IOException {
		String line;
		while ((line = in.readLine()) != null) {
            lineNo++;
            if (line.length() < 1)
                continue;
            parseDependencyOrItemDefinition(validScopes, items, line,
                    encoding);
        }
		return lineNo;
	}

	private BufferedReader getBufferedIOReader(File file) {
		if (file == null)
			throw new DtException(
					"could not read text file input: file name not specified");
		try {
			return new BufferedReader(new FileReader(file.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			throw new DtException("could not read from file "
					+ file.getAbsolutePath() + ": " + e.getMessage());
		}
	}

	private void addItemScopeToValidScopes(ValidScopes validScopes, Item item) {
		String scopeExisting = validScopes.getScopeName(item.getScopeIndex());
		if (scopeExisting == null) {
			validScopes.setScopeName(item.getScope(), item.getScopeIndex());
		} else {
			if (!scopeExisting.equals(item.getScope()))
				throw new DtException("scope \"" + item.getScope()
						+ "\" already exists with the name \"" + scopeExisting
						+ "\" at level " + (item.getScopeIndex()+1));
		}
	}

	private Item getNewItem(ValidScopes validScopes, String scope,
			String displayname, String[] parentDisplaynames, String encoding) {
		Item item = new Item(scope, displayname, parentDisplaynames, encoding);
		addItemScopeToValidScopes(validScopes, item);
		return item;
	}

	public List<Item> parseItem(ValidScopes validScopes, String itemDefinition,
			String encoding) {

		final String nonItemCharsRegex = "\\{}:\\s";
		final String anyNonItemCharRegex = "[" + nonItemCharsRegex + "]";
		final String anyNonItemCharAtLeastOnceRegex = anyNonItemCharRegex + "+";
		final String anyItemCharRegex = "[^" + nonItemCharsRegex + "]";
		final String anyItemRegex = "[\\s]*" + anyItemCharRegex + "+"
				+ "[\\s]*";
		final String anyNumberOfItemsRegex = "[\\s]*(" + anyItemRegex + ")*";
		final String onceOrNotAtAllItemsRegex = "[\\s]*(" + anyItemRegex + ")?";
		final String itemDefinitionWithScopeRegex = onceOrNotAtAllItemsRegex
				+ "\\{" + anyNumberOfItemsRegex + "}[\\s]*";
		final String itemDefinitionWithScopeAtLeastOnceRegex = "("
				+ itemDefinitionWithScopeRegex + ")+";
		final String itemDefinitionWithoutScopeRegex = "(" + anyItemRegex
				+ ")+";

		if (itemDefinition == null)
			throw new DtException("invalid item definition: null");

		List<Item> items = new ArrayList<>();
		String[] parents = null;
		String item;
		String scope;

		if (itemDefinition.matches(itemDefinitionWithScopeAtLeastOnceRegex)) {
			Pattern p = Pattern.compile(itemDefinitionWithScopeRegex);
			Matcher m = p.matcher(itemDefinition);
			while (m.find()) {
				String[] words = itemDefinition.substring(m.start(), m.end())
						.trim().split(anyNonItemCharAtLeastOnceRegex);
				if (words.length < 2) {
					throw new DtException("invalid item definition: \""
							+ itemDefinition + "\"");
				}
				scope = words[0];
				item = words[words.length - 1];
				if (words.length > 2) {
					parents = new String[words.length - 2];
					for (int iParent = 0, iWord = 0; iWord < words.length; iWord++) {
						if (iWord > 0 && iWord < words.length - 1)
							parents[iParent++] = words[iWord];
					}
				}
				items.add(getNewItem(validScopes, scope, item, parents,
						encoding));
			}
			return items;
		} else if (itemDefinition.matches(itemDefinitionWithoutScopeRegex)) {
			scope = "";
			String[] words = itemDefinition.trim().split(
					anyNonItemCharAtLeastOnceRegex);
			if (words.length <= 0) {
				throw new DtException("invalid item definition: \""
						+ itemDefinition + "\"");
			}
			for (String word : words) {
				if (word == null || word.length() == 0)
					continue;
				items.add(getNewItem(validScopes, scope, word.trim(), parents,
						encoding));
			}
			return items;
		} else {
			throw new DtException("invalid item definition: \""
					+ itemDefinition + "\"");
		}

	}

	private Item getExistingItemFromSet(Set<Item> items, Item item) {
		if (items != null) {
			for (Item itemInSet : items) {
				if (itemInSet == null)
					continue;
				if (itemInSet.equals(item)) {
					return itemInSet;
				}
			}
		}
		return item;
	}

	private void saveItemsToSet(Set<Item> allItems,
			List<Item> itemDependantList, List<Item> itemDependeeList) {
		for (Item itemDependant : itemDependantList) {
			if (allItems.contains(itemDependant)) {
				itemDependant = getExistingItemFromSet(allItems, itemDependant);
			} else {
				allItems.add(itemDependant);
			}
			if (itemDependeeList != null) {
				for (Item itemDependee : itemDependeeList) {
					if (allItems.contains(itemDependee)) {
						itemDependee = getExistingItemFromSet(allItems,
								itemDependee);
					} else {
						allItems.add(itemDependee);
					}
					itemDependant.addDependency(itemDependee);
				}
			}
		}
	}

	private void parseDependencyOrItemDefinition(ValidScopes validScopes,
			Set<Item> allItems, String dependencyOrItemDefinition,
			String encoding) {
		final String itemDelimiterRegex = ":";
		if (dependencyOrItemDefinition == null)
			throw new DtException("invalid dependency or item definition: null");
		String[] items = dependencyOrItemDefinition.split(itemDelimiterRegex);
		if (!(items.length >= 1 && items.length <= 2))
			throw new DtException("invalid dependency or item definition: \""
					+ dependencyOrItemDefinition + "\"");
		if (items.length == 1) {
			// item definition
			saveItemsToSet(allItems,
					parseItem(validScopes, items[0], encoding), null);
		} else {
			// item dependency definition
			saveItemsToSet(allItems,
					parseItem(validScopes, items[0], encoding), parseItem(
							validScopes, items[1], encoding));
		}
	}
}