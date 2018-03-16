//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm;

import java.util.List;
import java.util.Set;

import org.hjug.dtangler.core.analysisresult.AnalysisResult;
import org.hjug.dtangler.core.dependencies.Dependable;
import org.hjug.dtangler.core.dependencies.Dependency;
import org.hjug.dtangler.core.dsm.Dsm;

public interface DsmGuiModel {

	public enum DisplayNameFormat {
		full, shortened
	}

	void setDsm(Dsm dsm, AnalysisResult analysisResult);

	int getColumnCount();

	int getRowCount();

	DependableInfo getColumnInfo(int index);

	DependableInfo getRowInfo(int index);

	CellInfo getCellInfo(int row, int col);

	void selectCells(List<Integer> selectedRows, List<Integer> selectedCols);

	List<Integer> getSelectedRows();

	List<Integer> getSelectedCols();

	void clearSelection();

	void addChangeListener(DsmGuiModelChangeListener listener);

	Set<Dependency> getSelectionDependencies();

	Set<Dependable> getSelectionDependables();

	void setDisplayNameFormat(DisplayNameFormat nameFormat);

	DisplayNameFormat getDisplayNameFormat();
}
