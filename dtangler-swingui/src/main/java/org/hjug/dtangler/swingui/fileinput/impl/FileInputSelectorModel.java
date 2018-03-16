//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.fileinput.impl;

import java.util.List;

import org.hjug.dtangler.swingui.fileinput.FileInputSelection;

public interface FileInputSelectorModel {

	List<String> getEngines();

	String getEngine();

	void setEngine(String engine);

	List<String> getPaths();

	List<String> getMasks();

	void removePaths(List<String> paths);

	void removeMasks(List<String> masks);

	boolean isValidInputSelection();

	void addPath();

	void addMask();

	FileInputSelection getAppliedInputSelection();

	void applySelection();

}
