//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.textinput.impl;

class TextInputPresenter {

	private final TextInputView view;
	private final TextInputModel model;

	TextInputPresenter(TextInputView view, TextInputModel model) {
		this.view = view;
		this.model = model;
		view.setTitle(model.getTitle());
		view.setFieldName(model.getFieldName());
	}

	void onOk() {
		if (!canOk())
			return;
		model.setValue(view.getValue());
	}

	boolean canOk() {
		return !view.getValue().equals("");
	}
}