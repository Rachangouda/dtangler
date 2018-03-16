//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulesselector.impl;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.groupselector.GroupSelector;
import org.hjug.dtangler.swingui.rulememberselector.RuleMemberSelector;
import org.hjug.dtangler.swingui.rulesselector.RulesSelector;
import org.hjug.dtangler.swingui.windowmanager.WindowManager;

public class RulesSelectorImpl implements RulesSelector {

	private final WindowManager windowManager;
	private final RuleMemberSelector ruleMemberSelector;
	private final GroupSelector groupSelector;

	public RulesSelectorImpl(RuleMemberSelector ruleMemberSelector,
			WindowManager windowManager, GroupSelector groupSelector) {
		this.ruleMemberSelector = ruleMemberSelector;
		this.windowManager = windowManager;
		this.groupSelector = groupSelector;
	}

	public Arguments selectRules(Arguments previousArguments) {
		ActionFactory actionFactory = new ActionFactory();
		SwingRulesView view = new SwingRulesView(actionFactory);
		DefaultRulesModel model = new DefaultRulesModel(ruleMemberSelector,
				previousArguments, groupSelector);
		RulesPresenter presenter = new RulesPresenter(view, model);
		new RulesGlue(actionFactory, presenter, windowManager, view);
		windowManager.showModal(view);
		return model.getArguments();
	}

}
