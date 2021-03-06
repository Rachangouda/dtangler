// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulememberselector.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.rulememberselector.impl.RuleMemberView.Actions;
import org.hjug.dtangler.swingui.windowmanager.SwingView;
import org.hjug.dtangler.swingui.windowmanager.WindowManager;

public class RuleMemberGlue {

	private final ActionFactory actionFactory;
	private final RuleMemberPresenter presenter;

	public RuleMemberGlue(ActionFactory actionFactory,
			final RuleMemberPresenter presenter,
			final WindowManager windowManager, final SwingView view) {
		this.actionFactory = actionFactory;
		this.presenter = presenter;
		updateActionStates();

		actionFactory.setImplementation(Actions.ok, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				presenter.onOk();
				windowManager.close(view);
			}
		});

		actionFactory.setImplementation(Actions.cancel, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				windowManager.close(view);
			}
		});

		actionFactory.setImplementation(Actions.updateActionStates,
				new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						updateActionStates();
					}

				});

	}

	private void updateActionStates() {
		actionFactory.getAction(Actions.ok).setEnabled(presenter.canOk());
	}

}
