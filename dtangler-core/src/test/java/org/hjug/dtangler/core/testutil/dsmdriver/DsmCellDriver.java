// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.testutil.dsmdriver;

import org.hjug.dtangler.core.dsm.DsmCell;

public class DsmCellDriver {

	private final DsmCell dsmCell;

	public DsmCellDriver(DsmCell dsmCell) {
		this.dsmCell = dsmCell;
	}

	public int getWeight() {
		return dsmCell.getDependencyWeight();
	}

}
