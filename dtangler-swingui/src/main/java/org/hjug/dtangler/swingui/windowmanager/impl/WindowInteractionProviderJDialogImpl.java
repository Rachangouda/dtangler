//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.windowmanager.impl;

import javax.swing.JDialog;

import org.hjug.dtangler.swingui.windowmanager.WindowInteractionProvider;

public class WindowInteractionProviderJDialogImpl implements
		WindowInteractionProvider {

	private final JDialog dlg;

	public WindowInteractionProviderJDialogImpl(JDialog dlg) {
		this.dlg = dlg;
	}

	public void updateTitle(String newTitle) {
		dlg.setTitle(newTitle);
	}

}
