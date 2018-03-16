// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.textui;

public class SysoutWriter implements Writer {
	public void print(String s) {
		System.out.print(s);
	}

	public void println(String s) {
		System.out.println(s);
	}

}
