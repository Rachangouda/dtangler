// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.testutil.output;

import org.hjug.dtangler.core.textui.Writer;

public class MockWriter implements Writer {
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private StringBuilder output = new StringBuilder();

	public void print(String s) {
		output.append(s);
	}

	public void println(String s) {
		output.append(s);
		output.append(LINE_SEPARATOR);
	}

	public String getOutput() {
		return output.toString();
	}
}