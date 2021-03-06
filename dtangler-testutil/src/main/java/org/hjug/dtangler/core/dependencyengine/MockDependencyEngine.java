// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.dependencyengine;

import java.util.Arrays;
import java.util.List;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencies.Dependencies;

public class MockDependencyEngine extends AbstractDependencyEngine {

	private Dependencies dependencies = new Dependencies();

	public Dependencies getDependencies(Arguments arguments) {
		return dependencies;
	}

	public void setDependencies(Dependencies dependencies) {
		this.dependencies = dependencies;
	}

	public ArgumentsMatch getArgumentsMatchThisEngineExt(Arguments arguments) {
		return ArgumentsMatch.yes;
	}

	public List<String> getInputFileNameExtensions() {
		return Arrays.asList(".mock");
	}

	public boolean isDirectoryInputSupported() {
		return false;
	}

	public String getInputFilesDescription() {
		return "Mock test file";
	}

}
