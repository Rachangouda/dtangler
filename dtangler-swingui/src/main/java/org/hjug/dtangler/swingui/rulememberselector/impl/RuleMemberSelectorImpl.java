// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulememberselector.impl;

import java.util.List;

import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.rulememberselector.RuleMemberSelector;
import org.hjug.dtangler.swingui.windowmanager.WindowManager;

public class RuleMemberSelectorImpl implements RuleMemberSelector {

	private final WindowManager windowManager;

	public RuleMemberSelectorImpl(WindowManager windowManager) {
		this.windowManager = windowManager;
	}

	public String selectRuleMember(List<String> groupNames) {
		ActionFactory actionFactory = new ActionFactory();
		SwingRuleMemberView view = new SwingRuleMemberView(actionFactory);
		RuleMemberModel model = new RuleMemberModel(groupNames);
		RuleMemberPresenter presenter = new RuleMemberPresenter(view, model);
		new RuleMemberGlue(actionFactory, presenter, windowManager, view);
		windowManager.showModal(view);
		return model.getValue();
	}

}
