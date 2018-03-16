//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.textinput.impl;

import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.textinput.TextInputSelector;
import org.hjug.dtangler.swingui.windowmanager.WindowManager;

public class TextInputSelectorImpl implements TextInputSelector {

	private final WindowManager windowManager;

	public TextInputSelectorImpl(WindowManager windowManager) {
		this.windowManager = windowManager;
	}

	public String selectValue(String fieldName, String dialogTitle) {
		ActionFactory actionFactory = new ActionFactory();
		SwingTextInputView view = new SwingTextInputView(actionFactory);
		TextInputModel model = new TextInputModel(fieldName, dialogTitle);
		TextInputPresenter presenter = new TextInputPresenter(view, model);
		new TextInputGlue(actionFactory, presenter, view, windowManager);
		windowManager.showModal(view);
		return model.getValue();
	}

}
