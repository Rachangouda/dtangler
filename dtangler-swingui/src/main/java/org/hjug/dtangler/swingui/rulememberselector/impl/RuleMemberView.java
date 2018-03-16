// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulememberselector.impl;

import java.util.List;

import org.hjug.dtangler.swingui.actionfactory.ActionKey;

public interface RuleMemberView {

	enum Actions implements ActionKey {
		ok, cancel, updateActionStates;
	}

	enum MemberType {
		group, literal
	}

	void setGroupNames(List<String> groupNames);

	MemberType getSelectedMemberType();

	String getLiteral();

	String getSelectedGroup();

}
