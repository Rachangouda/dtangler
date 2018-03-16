//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.testutil;

import java.io.File;
import java.io.IOException;

public class ClassPathEntryFinder {

	/**
	 * The returned path is always an absolute path
	 */
	public static String getPathContaining(String entry) {
		String cp = System.getProperty("java.class.path");
		String[] paths = cp.split(File.pathSeparator);
		for (String path : paths) {
			if (path.contains(entry))
				return makeAbsolute(path);
		}
		throw new RuntimeException("path not found from classpath:" + entry);
	}

	private static String makeAbsolute(String path) {
		try {
			return new File(path).getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException("cannot get canonical path for:" + path, e);
		}
	}

}
