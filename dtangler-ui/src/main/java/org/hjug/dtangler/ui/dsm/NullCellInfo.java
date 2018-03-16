//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm;

public class NullCellInfo {

	public static final CellInfo instance = new CellInfo() {

		public AnalysisResultInfo getAnalysisResultInfo() {
			throw new UnsupportedOperationException("NullCellInfo");
		}

		public int getDependencyWeight() {
			throw new UnsupportedOperationException("NullCellInfo");
		}

		public boolean isInCrossHair() {
			throw new UnsupportedOperationException("NullCellInfo");
		}

		public boolean isSelected() {
			throw new UnsupportedOperationException("NullCellInfo");
		}
	};

	private NullCellInfo() {
	};

}
