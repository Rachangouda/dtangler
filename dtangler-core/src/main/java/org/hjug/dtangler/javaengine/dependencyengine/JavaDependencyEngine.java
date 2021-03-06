// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.javaengine.dependencyengine;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencies.Dependencies;
import org.hjug.dtangler.core.dependencies.Scope;
import org.hjug.dtangler.core.dependencyengine.AbstractDependencyEngine;
import org.hjug.dtangler.core.dependencyengine.DependencyEngine;
import org.hjug.dtangler.core.exception.DtException;
import org.hjug.dtangler.core.filefinder.FullPathWildCardFileFilter;
import org.hjug.dtangler.core.filefinder.RecursiveFileFinder;
import org.hjug.dtangler.javaengine.classfileparser.ClassFileParser;
import org.hjug.dtangler.javaengine.jarfileparser.JarFileParser;
import org.hjug.dtangler.javaengine.types.JavaClass;
import org.hjug.dtangler.javaengine.types.JavaScope;

public class JavaDependencyEngine extends AbstractDependencyEngine {

	private Scope getDefaultScope(Arguments arguments) {
		if (JavaScope.classes.getDisplayName().equalsIgnoreCase(
				arguments.getScope()))
			return JavaScope.classes;
		if (JavaScope.locations.getDisplayName().equalsIgnoreCase(
				arguments.getScope()))
			return JavaScope.locations;
		return JavaScope.packages;
	}

	public Dependencies getDependencies(Arguments arguments) {
		Dependencies dependencies = new ClassDependencies(getJavaClasses(arguments))
				.getDependencies();
		dependencies.setDefaultScope(getDefaultScope(arguments));
		return dependencies;
	}

	public ArgumentsMatch getArgumentsMatchThisEngineExt(Arguments arguments) {
		if (arguments == null)
			throw new DtException("invalid arguments: null");
		DependencyEngine.ArgumentsMatch argumentsMatch = ArgumentsMatch.no;
		for (String path : arguments.getInput()) {
			if (path == null)
				continue;
			if (path.toLowerCase().endsWith(".jar")
					|| path.toLowerCase().endsWith(".class")) {
				argumentsMatch = DependencyEngine.ArgumentsMatch.yes;
			} else {
				File file = new File(path);
				if (!file.isDirectory()) {
					return DependencyEngine.ArgumentsMatch.no;
				} else {
					if (argumentsMatch == ArgumentsMatch.no)
						argumentsMatch = ArgumentsMatch.maybe;
				}
			}
		}
		return argumentsMatch;
	}

	public List<String> getInputFileNameExtensions() {
		return Arrays.asList("jar", "class");
	}

	public boolean isDirectoryInputSupported() {
		return true;
	}

	private Set<JavaClass> getJavaClasses(Arguments arguments) {
		RecursiveFileFinder fileFinder = new RecursiveFileFinder();
		fileFinder.setFilter(new FullPathWildCardFileFilter(Arrays.asList(
				".class", ".jar"), arguments.getIgnoredFileMasks()));
		for (String path : arguments.getInput())
			fileFinder.findFiles(path);

		Set<JavaClass> classes = new HashSet<>();
		for (File file : fileFinder.getFiles()) {
			if (file.getName().endsWith(".class")) {
				classes.add(getDataFromClassFile(file, fileFinder));
			} else {
				classes.addAll(getDataFromJarFile(file));
			}
		}
		return classes;
	}

	private Set<JavaClass> getDataFromJarFile(File file) {
		try {
			Set<JavaClass> jarContents = new JarFileParser().parse(file);
			for (JavaClass clazz : jarContents) {
				clazz.setLocation(file.getAbsolutePath());
			}
			return jarContents;
		} catch (IOException e) {
			throw new DtException("Jar file could not be read: "
					+ file.getAbsolutePath(), e);
		}
	}

	private JavaClass getDataFromClassFile(File file,
			RecursiveFileFinder fileFinder) {
		JavaClass parsed = new ClassFileParser().parse(file);
		parsed.setLocation(fileFinder.getFilesWithPaths().get(file));
		return parsed;
	}

	public String getInputFilesDescription() {
		return "Java binary file";
	}

}
