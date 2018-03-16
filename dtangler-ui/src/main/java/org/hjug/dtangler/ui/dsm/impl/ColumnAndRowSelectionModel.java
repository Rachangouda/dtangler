//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm.impl;

public interface ColumnAndRowSelectionModel {

	boolean isRowOrColumnInCrossHair(int row, int col);

	boolean isSelected(int row, int col);

}
