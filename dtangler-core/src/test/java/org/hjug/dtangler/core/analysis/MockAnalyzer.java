//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.analysis;

import org.hjug.dtangler.core.dependencies.Dependencies;

public class MockAnalyzer extends DependencyAnalyzer {

	private boolean isValid;

	public MockAnalyzer() {
		this(true);
	}

	public MockAnalyzer(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValidResult() {
		return isValid;
	}

	@Override
	public void doAnalyze(Dependencies dependencies) {

	}

}
