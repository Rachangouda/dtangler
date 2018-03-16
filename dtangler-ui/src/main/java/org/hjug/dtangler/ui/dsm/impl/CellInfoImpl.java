//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm.impl;

import org.hjug.dtangler.core.dsm.DsmCell;
import org.hjug.dtangler.ui.dsm.AnalysisResultInfo;
import org.hjug.dtangler.ui.dsm.CellInfo;

public class CellInfoImpl implements CellInfo {

	private final DsmCell cell;
	private final AnalysisResultInfo analysisResultInfo;
	private final int row;
	private final int col;
	private final ColumnAndRowSelectionModel columnAndRowSelectionModel;

	public CellInfoImpl(DsmCell cell, AnalysisResultInfo analysisResultInfo,
			int row, int col,
			ColumnAndRowSelectionModel columnAndRowSelectionModel) {
		this.cell = cell;
		this.row = row;
		this.col = col;
		this.analysisResultInfo = analysisResultInfo;
		this.columnAndRowSelectionModel = columnAndRowSelectionModel;
	}

	public AnalysisResultInfo getAnalysisResultInfo() {
		return analysisResultInfo;
	}

	public int getDependencyWeight() {
		return cell.getDependencyWeight();
	}

	public boolean isInCrossHair() {
		return columnAndRowSelectionModel.isRowOrColumnInCrossHair(row, col);
	}

	public boolean isSelected() {
		return columnAndRowSelectionModel.isSelected(row, col);
	}

}
