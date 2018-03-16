// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulememberselector.impl;

import org.hjug.dtangler.swingui.rulememberselector.impl.RuleMemberView.MemberType;

public class RuleMemberPresenter {

	private final RuleMemberView view;
	private RuleMemberModel model;

	public RuleMemberPresenter(RuleMemberView view, RuleMemberModel model) {
		this.view = view;
		this.model = model;
		view.setGroupNames(model.getGroupNames());
	}

	public void onOk() {
		if (view.getSelectedMemberType().equals(MemberType.literal))
			model.setLiteral(view.getLiteral());
		else
			model.setGroupName(view.getSelectedGroup());
	}

	public boolean canOk() {
		if (view.getSelectedMemberType().equals(MemberType.literal))
			return view.getLiteral().length() > 0;
		return view.getSelectedGroup() != null;
	}
}
