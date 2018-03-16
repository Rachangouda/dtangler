// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.genericengine.dependencyengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hjug.dtangler.core.dependencies.Dependable;
import org.hjug.dtangler.core.dependencies.Dependencies;
import org.hjug.dtangler.genericengine.types.Item;
import org.hjug.dtangler.genericengine.types.ItemScope;
import org.hjug.dtangler.genericengine.types.ValidScopes;

public class ItemDependencyBuilder {

	public static ItemDependencyBuilder getInstance() {
		return new ItemDependencyBuilder();
	}

	private Dependable getDependable(
			Map<String, Map<String, Dependable>> dependables, String itemScope,
			int scopeIndex, String fullyqualifiedname, String displayname,
			String encoding, int contentCount) {
		Map<String, Dependable> scopeDependables = dependables.computeIfAbsent(itemScope, k -> new HashMap<>());
		return scopeDependables.computeIfAbsent(fullyqualifiedname, f -> new Dependable(new ItemScope(itemScope, scopeIndex),
				Item.getFullyqualifiedDisplayname(f,
						encoding), displayname, contentCount));
	}

	private Dependable getDependable(
			Map<String, Map<String, Dependable>> dependables, Item item) {
		return getDependable(dependables, item.getScope(),
				item.getScopeIndex(), item.getFullyqualifiedname(), item
						.getDisplayname(), item.getEncoding(), item
						.getContentCount());
	}

	private Map<Dependable, Integer> getDependencies(
			Map<String, Map<String, Dependable>> dependables, Item item) {
		Map<Item, Integer> itemDependencies = item.getDependencies();
		Map<Dependable, Integer> deps = new HashMap<>();
		for (Item itemDependee : itemDependencies.keySet()) {
			Dependable dependeeDependable = getDependable(dependables,
					itemDependee);
			// TODO Dependencies does not understand different scopes between
			// the dependant and the dependee?
			deps.put(dependeeDependable, itemDependencies.get(itemDependee));
		}
		return deps;
	}

	private void addParents(ValidScopes validScopes,
			Dependable childDependable, String[] parentFullyqualifiednames,
			String parentDisplaynames[],
			Map<String, Map<String, Dependable>> dependables,
			Dependencies dependencies, String encoding) {
		if (parentFullyqualifiednames == null)
			return;
		for (int iParent = parentFullyqualifiednames.length - 1; iParent >= 0; iParent--) {
			int parentScopeIndex = childDependable.getScope().index() - 1;
			String parentScope = validScopes
					.getValidScopeName(parentScopeIndex);
			Dependable parentDependable = getDependable(dependables,
					parentScope, parentScopeIndex,
					parentFullyqualifiednames[iParent],
					parentDisplaynames[iParent], encoding, 0);
			dependencies.addChild(parentDependable, childDependable);
			parentDependable.setContentCount(dependencies.getChilds(
					parentDependable).size());
			childDependable = parentDependable;
		}
	}

	public Dependencies build(ValidScopes validScopes, Set<Item> items) {

		validScopes.generateScopeNamesForUndefinedScopeNames("scope #", "#");
		Map<String, Map<String, Dependable>> dependables = new HashMap<>();
		Dependencies dependencies = new Dependencies();

		for (Item item : items) {
			Dependable dependable = getDependable(dependables, item);
			dependencies.addDependencies(dependable, getDependencies(
					dependables, item));
			addParents(validScopes, dependable, item
					.getParentFullyqualifiednames(), item
					.getParentDisplaynames(), dependables, dependencies, item
					.getEncoding());
		}

		return dependencies;
	}

}
