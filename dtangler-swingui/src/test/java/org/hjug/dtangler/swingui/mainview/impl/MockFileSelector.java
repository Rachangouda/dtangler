// This product is provided under the terms of EPL (Eclipse Public License)
// version 2.0.
//
// The full license text can be read from:
// https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.mainview.impl;

import org.hjug.dtangler.swingui.fileselector.FileSelector;

public class MockFileSelector implements FileSelector {

	private String file;

	public String selectFile(String functionText, String fileSuffix,
			String fileDescription) {
		return file;
	}

	public void setNextFile(String file) {
		this.file = file;
	}

}
