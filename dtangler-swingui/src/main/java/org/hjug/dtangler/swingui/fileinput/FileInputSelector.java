//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.fileinput;

public interface FileInputSelector {

	/**
	 * @param defaultInput -
	 *            the default values used in the UI, or null if none
	 * @return inputSelection upon successful selection, null upon cancel
	 */
	FileInputSelection selectInput(FileInputSelection defaultInput);
}
