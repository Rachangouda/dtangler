//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.directoryselector.impl;

import javax.swing.JFileChooser;

public class TestableSwingDirectorySelector extends SwingDirectorySelector {

	private int nextDialogResult = JFileChooser.APPROVE_OPTION;

	@Override
	protected int show() {
		// Do not show dialog in Unit tests
		return nextDialogResult;
	}

	@Override
	protected int show(JFileChooser chooser) {
		// Do not show dialog in Unit tests
		return nextDialogResult;
	}

	JFileChooser getFileChooser() {
		return getChooser();
	}

	public void setNextDialogResult(int nextDialogResult) {
		this.nextDialogResult = nextDialogResult;
	}

}
