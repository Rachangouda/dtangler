//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.testutil.cyclic.part1;

import org.hjug.dtangler.core.testutil.cyclic.part2.CyclicB;

public class CyclicA {

	private CyclicB dependency;

	public CyclicA() {
		dependency.toString();
	}
}