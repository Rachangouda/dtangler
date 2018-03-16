// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.groupselector.impl;

import org.hjug.dtangler.swingui.actionfactory.ActionKey;

public interface GroupView {

	enum Actions implements ActionKey {
		cancel, ok, addItem, removeItems, addExcludedItem, removeExcludedItems, updateActionStates
	}

}
