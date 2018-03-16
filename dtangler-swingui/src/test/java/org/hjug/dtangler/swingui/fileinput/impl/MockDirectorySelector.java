//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.fileinput.impl;

import java.util.List;

import org.hjug.dtangler.swingui.directoryselector.DirectorySelector;

public class MockDirectorySelector implements DirectorySelector {

	private String value;

	public String selectDirectory() {
		return value;
	}

	public String selectDirectory(String dialogTitle,
			String fileTypesDescription, boolean isDirectoryInputAllowed,
			List<String> fileNameExtensions) {
		return value;
	}

	public void setNextValue(String value) {
		this.value = value;
	}

}
