//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulesselector.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.actionfactory.KeyActionAdapter;
import org.hjug.dtangler.swingui.resource.icons.IconKey;
import org.hjug.dtangler.swingui.windowmanager.SwingBaseView;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

public class SwingRulesView extends SwingBaseView implements RulesView {

	private final JButton okBtn;
	private final JButton cancelBtn;
	private final JButton newGroupBtn;
	private final JButton editGroupBtn;
	private final JButton removeGroupBtn;
	private final SwingRuleView forbiddenDeps;
	private final SwingRuleView allowedDeps;
	private final JList<String> groups = new JList<>();

	public SwingRulesView(ActionFactory actionFactory) {
		super(actionFactory);
		okBtn = createButton("Ok", Actions.ok);
		cancelBtn = createButton("Cancel", Actions.cancel);
		newGroupBtn = createButton("Add...", Actions.newGroup, IconKey.plus12);
		editGroupBtn = createButton("View/Edit...", Actions.editGroup);
		removeGroupBtn = createButton("Remove", Actions.removeGroups,
				IconKey.minus12);
		groups.setName("groups");
		groups.getSelectionModel().addListSelectionListener(
				createSelectionActionAdapter(Actions.updateActionStates));
		forbiddenDeps = new SwingRuleView(actionFactory,
				"Forbidden dependencies", "Forbidden dependency rules",
				"Cannot depend on", Actions.addForbiddenRule,
				Actions.removeForbiddenRule, Actions.addForbiddenRuleItem,
				Actions.removeForbiddenRuleItems,
				Actions.forbiddenRuleSelectionChanged);
		allowedDeps = new SwingRuleView(actionFactory, "Allowed dependencies",
				"Allowed dependency rules", "Can depend on",
				Actions.addAllowedRule, Actions.removeAllowedRule,
				Actions.addAllowedRuleItem, Actions.removeAllowedRuleItems,
				Actions.allowedRuleSelectionChanged);
		groups.addKeyListener(new KeyActionAdapter(KeyEvent.VK_INSERT,
				actionFactory.getAction(Actions.newGroup)));
		groups.addKeyListener(new KeyActionAdapter(KeyEvent.VK_DELETE,
				actionFactory.getAction(Actions.removeGroups)));
		groups.addKeyListener(new KeyActionAdapter(KeyEvent.VK_ENTER,
				actionFactory.getAction(Actions.editGroup)));
	}

	public Dimension getPreferredSize() {
		return new Dimension(450, 450);
	}

	public String getTitle() {
		return "Dependency rules";
	}

	protected JComponent buildViewComponent() {
		DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(
				"fill:10dlu:grow", "fill:10dlu:grow,4dlu,p"));
		builder.setDefaultDialogBorder();
		builder.append(createTabbedPane());
		builder.nextRow();
		builder.append(ButtonBarFactory.buildRightAlignedBar(okBtn, cancelBtn));

		JPanel panel = builder.getPanel();
		addCommonKeyEvent(panel, KeyEvent.VK_ESCAPE, Actions.cancel);
		return panel;
	}

	private Component createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Groups", createGroupPanel());
		tabbedPane.addTab(forbiddenDeps.getTitle(), forbiddenDeps
				.getViewComponent());
		tabbedPane.addTab(allowedDeps.getTitle(), allowedDeps
				.getViewComponent());
		tabbedPane.setSelectedIndex(1);
		return tabbedPane;
	}

	private Component createGroupPanel() {
		DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(
				"fill:p:grow,4dlu,p", "fill:p:grow"));
		builder.setDefaultDialogBorder();
		builder.append(new JScrollPane(groups), createButtonStack(newGroupBtn,
				editGroupBtn, removeGroupBtn));
		return builder.getPanel();
	}

	public void setGroupNames(List<String> groupNames) {
		groups.setListData(new Vector<>(groupNames));
	}

	public RuleView forbiddenDeps() {
		return forbiddenDeps;
	}

	public RuleView allowedDeps() {
		return allowedDeps;
	}

	public List<String> getSelectedGroups() {
		return groups.getSelectedValuesList();
	}

}
