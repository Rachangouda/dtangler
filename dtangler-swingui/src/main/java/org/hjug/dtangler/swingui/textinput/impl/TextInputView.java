//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.textinput.impl;

import org.hjug.dtangler.swingui.actionfactory.ActionKey;

interface TextInputView {

	enum Actions implements ActionKey {
		ok, cancel, updateActionStates
	}

	void setTitle(String title);

	void setFieldName(String fieldName);

	String getValue();
}