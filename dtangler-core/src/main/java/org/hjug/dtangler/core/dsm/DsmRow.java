// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.dsm;

import java.util.List;

import org.hjug.dtangler.core.dependencies.Dependable;

public class DsmRow {

	private final List<DsmCell> cells;
	private final Dependable dependee;

	public DsmRow(Dependable dependee, List<DsmCell> cells) {
		this.dependee = dependee;
		this.cells = cells;
	}

	public List<DsmCell> getCells() {
		return cells;
	}

	public Dependable getDependee() {
		return dependee;
	}
}
