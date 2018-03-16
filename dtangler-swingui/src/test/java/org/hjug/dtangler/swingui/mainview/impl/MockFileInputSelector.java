//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.mainview.impl;

import org.hjug.dtangler.swingui.fileinput.FileInputSelection;
import org.hjug.dtangler.swingui.fileinput.FileInputSelector;

public class MockFileInputSelector implements FileInputSelector {

	private FileInputSelection lastUsedDefaultInput;
	private FileInputSelection inputToReturn;

	public FileInputSelection selectInput(FileInputSelection defaultInput) {
		this.lastUsedDefaultInput = defaultInput;
		return inputToReturn;
	}

	public void setInputToReturn(FileInputSelection inputToReturn) {
		this.inputToReturn = inputToReturn;
	}

	public FileInputSelection getLastUsedDefaultInput() {
		return lastUsedDefaultInput;
	}

}
