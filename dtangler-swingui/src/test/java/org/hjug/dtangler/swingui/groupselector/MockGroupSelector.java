// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.groupselector;

import org.hjug.dtangler.core.configuration.Group;

public class MockGroupSelector implements GroupSelector {
	private Group nextResult;
	private Group lastEditedGroup;

	public Group createGroup() {
		return nextResult;
	}

	public void setNextResult(Group group) {
		this.nextResult = group;
	}

	public Group lastEditedGroup() {
		return lastEditedGroup;
	}

	public Group editGroup(Group group) {
		lastEditedGroup = group;
		return nextResult;
	}
}
