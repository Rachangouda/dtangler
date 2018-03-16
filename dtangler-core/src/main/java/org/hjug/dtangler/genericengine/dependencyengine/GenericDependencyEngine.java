// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.genericengine.dependencyengine;

import java.io.File;
import java.util.*;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencies.Dependencies;
import org.hjug.dtangler.core.dependencies.Scope;
import org.hjug.dtangler.core.dependencyengine.AbstractDependencyEngine;
import org.hjug.dtangler.core.dependencyengine.DependencyEngine;
import org.hjug.dtangler.core.exception.DtException;
import org.hjug.dtangler.core.filefinder.FullPathWildCardFileFilter;
import org.hjug.dtangler.core.filefinder.RecursiveFileFinder;
import org.hjug.dtangler.genericengine.dependenciesstreamparser.ItemDependenciesStreamParser;
import org.hjug.dtangler.genericengine.types.Item;
import org.hjug.dtangler.genericengine.types.ItemScope;
import org.hjug.dtangler.genericengine.types.ValidScopes;

public class GenericDependencyEngine extends AbstractDependencyEngine {

	private final static String INPUT_FILE_EXT = "dt";
	private final static String INPUT_FILE_TYPE = "." + INPUT_FILE_EXT;
	private final static String INPUT_STDIN = "stdin";

	private Scope getDefaultScope(Arguments arguments, ValidScopes validScopes) {
		String scopeName = arguments.getScope();
		if (scopeName == null) {
			if (validScopes.getNumberOfScopes() > 0) {
				return validScopes.getDefaultScope();
			}
			return new ItemScope("", 0);
		}
		return new ItemScope(scopeName, validScopes.getScopeIndex(scopeName));
	}

	public Dependencies getDependencies(Arguments arguments) {
		ValidScopes validScopes = new ValidScopes();
		Dependencies dependencies = ItemDependencyBuilder.getInstance().build(validScopes,
				getItems(arguments, validScopes));
		dependencies.setDefaultScope(getDefaultScope(arguments, validScopes));
		return dependencies;
	}

	public ArgumentsMatch getArgumentsMatchThisEngineExt(Arguments arguments) {
		DependencyEngine.ArgumentsMatch argumentsMatch = DependencyEngine.ArgumentsMatch.no;
		if (arguments == null)
			throw new DtException("invalid arguments: null");
		for (String path : arguments.getInput()) {
			if (path == null)
				continue;
			if (path.toLowerCase().endsWith(INPUT_FILE_TYPE)) {
				argumentsMatch = DependencyEngine.ArgumentsMatch.yes;
			} else {
				if (path.equalsIgnoreCase(INPUT_STDIN)) {
					if (argumentsMatch == ArgumentsMatch.no)
						argumentsMatch = DependencyEngine.ArgumentsMatch.maybe;
				} else {
					return ArgumentsMatch.no;
				}
			}
		}
		return argumentsMatch;
	}

	private boolean readFromStandardInput(Arguments arguments) {
		boolean readFromStandardInput = false;
		if (arguments.getInput() == null || arguments.getInput().size() == 0)
			throw new DtException("error: no input arguments");

		for (String input : arguments.getInput()) {
			if (input != null && input.equalsIgnoreCase(INPUT_STDIN)) {
				readFromStandardInput = true;
				break;
			}
		}
		return readFromStandardInput;
	}

	private Set<Item> getItems(Arguments arguments,
			ValidScopes validScopes) {
		ItemDependenciesStreamParser parser = new ItemDependenciesStreamParser();
		Set<Item> items = new HashSet<>();
		if (readFromStandardInput(arguments)) {
			items.addAll(parser.parse(validScopes, "UTF-8"));
		} else {
			Set<File> inputFiles = getInputFiles(arguments);
			if (inputFiles.size() == 0) {
				throw new DtException("unable to read the dependencies from "
						+ arguments.getInput());
			}
			for (File file : inputFiles) {
				items.addAll(parser.parse(validScopes, file, "UTF-8"));
			}
		}
		return items;
	}
	
	private Set<File> getInputFiles(Arguments arguments) {
		RecursiveFileFinder fileFinder = new RecursiveFileFinder();
		fileFinder.setFilter(new FullPathWildCardFileFilter(Collections.singletonList(INPUT_FILE_TYPE), arguments.getIgnoredFileMasks()));
		for (String path : arguments.getInput())
			fileFinder.findFiles(path);
		return fileFinder.getFiles();
	}

	public List<String> getInputFileNameExtensions() {
		return Collections.singletonList(INPUT_FILE_EXT);
	}

	public boolean isDirectoryInputSupported() {
		return false;
	}

	public String getInputFilesDescription() {
		return "Dependency definition file";
	}

}
