// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.dependencies;

public class TestDependable extends Dependable {

	public TestDependable(String name) {
		this(name, TestScope.scope1);
	}

	public TestDependable(String name, Scope scope) {
		super(scope, name, name, 0);
	}

}
