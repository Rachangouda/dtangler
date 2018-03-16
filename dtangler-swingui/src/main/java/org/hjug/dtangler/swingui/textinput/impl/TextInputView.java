//This product is provided under the terms of EPL (Eclipse Public License) 
//version 1.0.
//
//The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php

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