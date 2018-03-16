//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.fileinput.impl;

import java.util.List;

import org.hjug.dtangler.swingui.actionfactory.ActionKey;

public interface FileInputSelectorView {

	enum Actions implements ActionKey {
		cancel, selectEngine, addPath, removePath, addMask, removeMask, ok, updateActionStates
	}

	void setEngines(List<String> engines);

	void setEngineSelection(String engine);

	void setPaths(List<String> paths);

	void setMasks(List<String> masks);

	String getEngineSelection();

	List<String> getPathSelection();

	List<String> getMaskSelection();

}
