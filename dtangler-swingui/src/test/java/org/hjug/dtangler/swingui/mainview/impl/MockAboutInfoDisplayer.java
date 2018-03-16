//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.mainview.impl;

import org.hjug.dtangler.swingui.aboutinfodisplayer.AboutInfoDisplayer;

public class MockAboutInfoDisplayer implements AboutInfoDisplayer {

	private boolean infoDisplayed;

	public boolean wasAboutInfoDisplayed() {
		return infoDisplayed;
	}

	public void displayAboutInfo() {
		infoDisplayed = true;
	}

}
