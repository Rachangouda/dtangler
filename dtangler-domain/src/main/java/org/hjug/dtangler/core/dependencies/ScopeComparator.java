// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.dependencies;

import java.util.Comparator;

public class ScopeComparator implements Comparator<Scope> {

	public int compare(Scope s1, Scope s2) {
		return s1.index() - s2.index();
	}

}
