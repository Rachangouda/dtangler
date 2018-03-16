// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.dsm;

import java.util.List;

public class Dsm {

	private final List<DsmRow> rows;

	public Dsm(List<DsmRow> rows) {
		this.rows = rows;
	}

	public List<DsmRow> getRows() {
		return rows;
	}

}
