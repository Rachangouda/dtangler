//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm;

public interface DependableInfo {

	String getDisplayName();

	int getContentCount();

	int getIndex();

	boolean isInCrosshair();

	boolean isSelected();

	boolean containsErrors();

	boolean containsWarnings();

	String getFullyQualifiedName();

}
