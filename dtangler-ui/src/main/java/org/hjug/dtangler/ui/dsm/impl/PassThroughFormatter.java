// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm.impl;

public class PassThroughFormatter implements Formatter {

	public String format(Object value) {
		return value.toString();
	}

}
