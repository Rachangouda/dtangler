//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.aboutinfodisplayer.impl;

import org.hjug.dtangler.swingui.aboutinfodisplayer.AboutInfoDisplayer;
import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.windowmanager.WindowManager;

public class AboutInfoDisplayerImpl implements AboutInfoDisplayer {

	private final WindowManager windowManager;

	public AboutInfoDisplayerImpl(WindowManager windowManager) {
		this.windowManager = windowManager;
	}

	public void displayAboutInfo() {
		ActionFactory actionFactory = new ActionFactory();
		SwingAboutInfoView view = new SwingAboutInfoView(actionFactory);
		new AboutInfoGlue(actionFactory, windowManager, view);
		windowManager.showModal(view);
	}

}
