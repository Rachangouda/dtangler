//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.analysisresult;

import java.util.Set;

import org.hjug.dtangler.core.dependencies.Dependable;

public interface Violation {

	enum Severity {
		error, warning
	}

	/** @return a user-friendly text description of the violation */
	String asText();

	Severity getSeverity();

	boolean appliesTo(Set<Dependable> dependables);

	Set<Dependable> getMembers();

}
