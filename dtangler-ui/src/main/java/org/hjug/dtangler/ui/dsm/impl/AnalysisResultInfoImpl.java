//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm.impl;

import org.hjug.dtangler.ui.dsm.AnalysisResultInfo;

public class AnalysisResultInfoImpl implements AnalysisResultInfo {

	private final boolean hasWarnings;
	private final boolean hasErrors;

	public AnalysisResultInfoImpl(boolean hasErrors, boolean hasWarnings) {
		this.hasErrors = hasErrors;
		this.hasWarnings = hasWarnings;
	}

	public boolean hasErrors() {
		return hasErrors;
	}

	public boolean hasWarnings() {
		return hasWarnings;
	}

}
