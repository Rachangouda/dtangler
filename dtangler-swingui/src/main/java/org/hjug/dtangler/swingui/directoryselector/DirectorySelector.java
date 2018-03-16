//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.directoryselector;

import java.util.List;

public interface DirectorySelector {

	String selectDirectory();

	String selectDirectory(String dialogTitle, String fileTypesDescription, boolean isDirectoryInputAllowed, List<String> fileNameExtensions);

}
