//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm;

public class MockAnalysisResultInfo implements AnalysisResultInfo {

	private final boolean hasErrors;

	public MockAnalysisResultInfo(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public boolean hasErrors() {
		return hasErrors;
	}

	public boolean hasWarnings() {
		return false;
	}

}
