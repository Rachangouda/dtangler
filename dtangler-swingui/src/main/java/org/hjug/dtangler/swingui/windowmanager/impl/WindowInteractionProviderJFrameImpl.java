//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.windowmanager.impl;

import javax.swing.JFrame;

import org.hjug.dtangler.swingui.windowmanager.WindowInteractionProvider;

public class WindowInteractionProviderJFrameImpl implements
		WindowInteractionProvider {

	private final JFrame frame;

	public WindowInteractionProviderJFrameImpl(JFrame frame) {
		this.frame = frame;
	}

	public void updateTitle(String newTitle) {
		frame.setTitle(newTitle);
	}
}
