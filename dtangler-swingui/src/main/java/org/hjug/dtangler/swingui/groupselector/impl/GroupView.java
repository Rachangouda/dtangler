// This product is provided under the terms of EPL (Eclipse Public License) 
// version 1.0.
//
// The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php 

package org.hjug.dtangler.swingui.groupselector.impl;

import org.hjug.dtangler.swingui.actionfactory.ActionKey;

public interface GroupView {

	enum Actions implements ActionKey {
		cancel, ok, addItem, removeItems, addExcludedItem, removeExcludedItems, updateActionStates
	}

}
