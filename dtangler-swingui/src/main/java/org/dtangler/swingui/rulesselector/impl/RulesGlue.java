//This product is provided under the terms of EPL (Eclipse Public License) 
//version 1.0.
//
//The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php

package org.dtangler.swingui.rulesselector.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.dtangler.swingui.actionfactory.ActionFactory;
import org.dtangler.swingui.rulesselector.impl.RulesView.Actions;
import org.dtangler.swingui.windowmanager.SwingView;
import org.dtangler.swingui.windowmanager.WindowManager;

public class RulesGlue {

	private final ActionFactory actionFactory;
	private final RulesPresenter presenter;

	public RulesGlue(ActionFactory actionFactory,
			final RulesPresenter presenter, final WindowManager windowManager,
			final SwingView view) {
		this.actionFactory = actionFactory;
		this.presenter = presenter;
		updateActionStates();
		actionFactory.setImplementation(Actions.addForbiddenRule,
				e -> {
                    presenter.forbiddenDeps().onAddRule();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.removeForbiddenRule,
				e -> {
                    presenter.forbiddenDeps().onRemoveRule();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.addForbiddenRuleItem,
				e -> {
                    presenter.forbiddenDeps().onAddRuleItems();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.removeForbiddenRuleItems,
				e -> {
                    presenter.forbiddenDeps().onRemoveRuleItems();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.forbiddenRuleSelectionChanged,
				e -> presenter.forbiddenDeps().onRuleSelectionChanged());

		actionFactory.setImplementation(Actions.addAllowedRule,
				e -> {
                    presenter.allowedDeps().onAddRule();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.removeAllowedRule,
				e -> {
                    presenter.allowedDeps().onRemoveRule();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.addAllowedRuleItem,
				e -> {
                    presenter.allowedDeps().onAddRuleItems();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.removeAllowedRuleItems,
				e -> {
                    presenter.allowedDeps().onRemoveRuleItems();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.allowedRuleSelectionChanged,
				e -> presenter.allowedDeps().onRuleSelectionChanged());

		actionFactory.setImplementation(Actions.cancel, e -> windowManager.close(view));

		actionFactory.setImplementation(Actions.ok, e -> {
            presenter.onOk();
            windowManager.close(view);
        });

		actionFactory.setImplementation(Actions.updateActionStates,
				e -> updateActionStates());
		actionFactory.setImplementation(Actions.newGroup, e -> {
            presenter.onNewGroup();
            updateActionStates();
        });

		actionFactory.setImplementation(Actions.removeGroups,
				e -> {
                    presenter.onRemoveGroups();
                    updateActionStates();
                });

		actionFactory.setImplementation(Actions.editGroup,
				e -> {
                    presenter.onEditGroup();
                    updateActionStates();
                });
	}

	private void updateActionStates() {
		actionFactory.getAction(Actions.removeForbiddenRule).setEnabled(
				presenter.forbiddenDeps().canRemoveRule());
		actionFactory.getAction(Actions.addForbiddenRuleItem).setEnabled(
				presenter.forbiddenDeps().canAddRuleItems());
		actionFactory.getAction(Actions.removeForbiddenRuleItems).setEnabled(
				presenter.forbiddenDeps().canRemoveRuleItems());

		actionFactory.getAction(Actions.removeAllowedRule).setEnabled(
				presenter.allowedDeps().canRemoveRule());
		actionFactory.getAction(Actions.addAllowedRuleItem).setEnabled(
				presenter.allowedDeps().canAddRuleItems());
		actionFactory.getAction(Actions.removeAllowedRuleItems).setEnabled(
				presenter.allowedDeps().canRemoveRuleItems());

		actionFactory.getAction(Actions.editGroup).setEnabled(
				presenter.canEditGroup());

		actionFactory.getAction(Actions.removeGroups).setEnabled(
				presenter.canRemoveGroups());

	}

}
