// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.dependencies;

public enum TestScope implements Scope {
	scope1, scope2, scope3;

	public String getDisplayName() {
		return name();
	}

	public int index() {
		return ordinal();
	}
}
