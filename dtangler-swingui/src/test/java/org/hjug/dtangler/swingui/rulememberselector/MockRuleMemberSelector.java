// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulememberselector;

import java.util.List;

public class MockRuleMemberSelector implements RuleMemberSelector {

	private String nextValue;
	private List<String> groupNames;

	public String selectRuleMember(List<String> groupNames) {
		this.groupNames = groupNames;
		return nextValue;
	}

	public void setNextValue(String nextValue) {
		this.nextValue = nextValue;
	}

	public List<String> getLastUsedGroupNames() {
		return groupNames;
	}

}
