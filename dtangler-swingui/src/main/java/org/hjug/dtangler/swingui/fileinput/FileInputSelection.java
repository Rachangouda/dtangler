//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.fileinput;

import java.util.List;

public class FileInputSelection {

	private final List<String> paths;
	private final List<String> ignoredFileMasks;
	private String engine;

	public FileInputSelection(String engine, List<String> paths, List<String> ignoredFileMasks) {
		this.engine = engine;
		this.paths = paths;
		this.ignoredFileMasks = ignoredFileMasks;
	}

	public String getEngine() {
		return engine;
	}

	public List<String> getIgnoredFileMasks() {
		return ignoredFileMasks;
	}

	public List<String> getPaths() {
		return paths;
	}

}
