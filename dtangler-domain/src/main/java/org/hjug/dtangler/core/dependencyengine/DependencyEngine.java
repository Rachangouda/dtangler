// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.dependencyengine;

import java.util.List;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencies.Dependencies;

public interface DependencyEngine {
	
	enum ArgumentsMatch {
		yes, maybe, no
	}

	void setDependencyEngineId(String dependencyEngineId);
	
	String getDependencyEngineId();

	Dependencies getDependencies(Arguments arguments);

	ArgumentsMatch getArgumentsMatchThisEngine(Arguments arguments);

	List<String> getInputFileNameExtensions();

	String getInputFilesDescription();

	boolean isDirectoryInputSupported();

}
