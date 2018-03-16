//This product is provided under the terms of EPL (Eclipse Public License) 
//version 1.0.
//
//The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php

package org.hjug.dtangler.swingui.windowmanager.impl;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JMenuBar;

import org.hjug.dtangler.swingui.windowmanager.SwingView;
import org.hjug.dtangler.swingui.windowmanager.WindowInteractionProvider;

public class MockSwingView implements SwingView {

	private final Dimension preferredSize;
	private final String title;
	private final JComponent viewComponent;
	private final JMenuBar menu;

	public MockSwingView(String title, JComponent viewComponent,
			Dimension preferredSize) {
		this(title, viewComponent, preferredSize, null);
	}

	public MockSwingView(String title, JComponent viewComponent,
			Dimension preferredSize, JMenuBar menu) {
		this.menu = menu;
		this.preferredSize = preferredSize;
		this.title = title;
		this.viewComponent = viewComponent;
	}

	public Dimension getPreferredSize() {
		return preferredSize;
	}

	public String getTitle() {
		return title;
	}

	public JComponent getViewComponent() {
		return viewComponent;
	}

	public JMenuBar getMenuBar() {
		return menu;
	}

	public void setWindowInteractionProvider(
			WindowInteractionProvider windowInteractionProvider) {
	}

	public Component getFirstComponentToFocus() {
		return null;
	}

}
