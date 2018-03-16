//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm;

public class MockCellInfo implements CellInfo {

	private final int depWeight;
	private final boolean isRowOrColumnSelected;
	private final AnalysisResultInfo analysisResultInfo;

	public MockCellInfo(int depWeight, boolean isRowOrColumnSelected,
			boolean hasErrors) {
		this.depWeight = depWeight;
		this.isRowOrColumnSelected = isRowOrColumnSelected;
		this.analysisResultInfo = new MockAnalysisResultInfo(hasErrors);
	}

	public AnalysisResultInfo getAnalysisResultInfo() {
		return analysisResultInfo;
	}

	public int getDependencyWeight() {
		return depWeight;
	}

	public boolean isInCrossHair() {
		return isRowOrColumnSelected;
	}

	public boolean isSelected() {
		return false;
	}

}
