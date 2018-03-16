//This product is provided under the terms of EPL (Eclipse Public License) 
//version 1.0.
//
//The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php

package org.hjug.dtangler.swingui.textinput.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.textinput.impl.TextInputView.Actions;
import org.hjug.dtangler.swingui.windowmanager.SwingView;
import org.hjug.dtangler.swingui.windowmanager.WindowManager;

public class TextInputGlue {

	private final TextInputPresenter presenter;
	private final ActionFactory actionFactory;

	public TextInputGlue(ActionFactory actionFactory,
			final TextInputPresenter presenter, final SwingView view,
			final WindowManager windowManager) {
		this.actionFactory = actionFactory;
		this.presenter = presenter;
		updateActionStates();

		actionFactory.setImplementation(Actions.ok, e -> {
            presenter.onOk();
            windowManager.close(view);
        });

		actionFactory.setImplementation(Actions.cancel, e -> windowManager.close(view));

		actionFactory.setImplementation(Actions.updateActionStates,
				e -> updateActionStates());

	}

	private void updateActionStates() {
		actionFactory.setEnabled(Actions.ok, presenter.canOk());
	}

}
