// This product is provided under the terms of EPL (Eclipse Public License) 
// version 1.0.
//
// The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php 

package org.hjug.dtangler.core.dsm;

import org.hjug.dtangler.core.dependencies.Dependable;
import org.hjug.dtangler.core.dependencies.Dependency;

public class DsmCell {

	private final int dependencyCount;
	private final Dependable dependant;
	private final Dependable dependee;

	public DsmCell(Dependable dependant, Dependable dependee,
			int dependencyCount) {
		this.dependant = dependant;
		this.dependee = dependee;
		this.dependencyCount = dependencyCount;
	}

	public int getDependencyWeight() {
		return dependencyCount;
	}

	public boolean isValid() {
		return dependant != null && !dependant.equals(dependee);
	}

	public Dependency getDependency() {
		return new Dependency(dependant, dependee);
	}
}
