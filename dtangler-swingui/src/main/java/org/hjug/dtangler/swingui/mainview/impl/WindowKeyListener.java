// This product is provided under the terms of EPL (Eclipse Public License)
// version 2.0.
//
// The full license text can be read from:
// https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.mainview.impl;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.Action;

public class WindowKeyListener extends EventQueue {
	final Hashtable<Integer, Action> actions = new Hashtable<Integer, Action>();
	final private Component parent;

	public WindowKeyListener(Component parent) {
		super();
		this.parent = parent;
	}

	public void addKeyAction(int keyCode, Action action) {
		actions.put(keyCode, action);
	}

	protected void dispatchEvent(AWTEvent event) {
		try {
			if (!event.getSource().equals(this.parent))
				return;
			if (!(event instanceof KeyEvent))
				return;
			if (event.getID() != KeyEvent.KEY_RELEASED)
				return;
			handleKeyEvent((KeyEvent) event);
		} finally {
			super.dispatchEvent(event);
		}
	}

	private void handleKeyEvent(KeyEvent event) {
		Integer key = event.getKeyCode();

		if (!actions.contains(key))
			return;
		actions.get(key).actionPerformed(
				new ActionEvent(event.getSource(), event.getID(), ""));
	}
}
