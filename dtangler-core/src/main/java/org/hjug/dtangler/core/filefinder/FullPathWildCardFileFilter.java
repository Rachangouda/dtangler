// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.filefinder;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import org.hjug.dtangler.core.util.WildcardMatch;

public class FullPathWildCardFileFilter implements FileFilter {

	private final List<String> masksToIgnore;
	private final List<String> extensions;

	/**
	 * 
	 * @param extensions
	 *            file extensions that the file must have in order to even be
	 *            validated
	 * @param masksToIgnore
	 *            masks against the full path+filename are compared. If a
	 *            filename matches one or more of the masks, it is filtered out.
	 *            Wildcards ('*') are allowed in any position of theinput string
	 */
	public FullPathWildCardFileFilter(List<String> extensions,
			List<String> masksToIgnore) {
		this.extensions = extensions;
		this.masksToIgnore = masksToIgnore;

	}

	public boolean accept(File pathname) {
		return isValidExtension(pathname.getName()) && !isMatch(pathname.getAbsolutePath());
	}

	private boolean isValidExtension(String name) {
		for (String extension : extensions) {
			if (name.toLowerCase().endsWith(extension))
				return true;
		}
		return false;
	}

	private boolean isMatch(String absolutePath) {
		for (String mask : masksToIgnore)
			if (isMatch(absolutePath, mask))
				return true;
		return false;
	}

	private boolean isMatch(String absolutePath, String mask) {
		return absolutePath.equals(mask) || new WildcardMatch(mask).isMatch(absolutePath);
	}

}
