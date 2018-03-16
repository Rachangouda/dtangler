// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.rulememberselector.impl;

import java.util.List;

import org.hjug.dtangler.core.exception.DtException;

public class RuleMemberModel {

	private final List<String> groupNames;
	private String value;

	public RuleMemberModel(List<String> groupNames) {
		this.groupNames = groupNames;
	}

	public List<String> getGroupNames() {
		return groupNames;
	}

	public void setLiteral(String literal) {
		if (literal.contains("@"))
			throw new DtException("Item name cannot contain the character '@'");
		value = literal;
	}

	public void setGroupName(String groupName) {
		value = "@" + groupName;
	}

	public String getValue() {
		return value;
	}

}
