//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulesselector.impl;

import java.util.List;

public interface RulesModel {

	public interface RuleModel {

		void addRule();

		List<String> getRules();

		void removeRules(List<String> rulesToRemove);

		List<String> getRuleItems(List<String> rules);

		void addRuleItem(String ruleToAddTo);

		void removeRuleItem(String ruleToRemoveFrom, List<String> ruleItems);

	}

	void save();

	List<String> getGroupNames();

	RuleModel forbiddenDepsModel();

	RuleModel allowedDepsModel();

	void newGroup();

	void removeGroups(List<String> groupNames);

	void editGroup(String groupName);

}
