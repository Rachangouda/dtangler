//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.textinput.impl;

public class MockTextInputView implements TextInputView {

	private String title;
	private String fieldName;
	private String value;

	public String getValue() {
		return value;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getTitle() {
		return title;
	}

}
