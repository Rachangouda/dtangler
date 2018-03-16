//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.textinput.impl;

class TextInputModel {

	private final String fieldName;
	private final String dialogTitle;
	private String value;

	TextInputModel(String fieldName, String title) {
		this.fieldName = fieldName;
		this.dialogTitle = title;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getTitle() {
		return dialogTitle;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}