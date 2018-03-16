//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.textinput;

public class MockTextInputSelector implements TextInputSelector {

	private String value;

	public String selectValue(String fieldName, String dialogTitle) {
		return value;
	}

	public void setNextValue(String value) {
		this.value = value;
	}

}
