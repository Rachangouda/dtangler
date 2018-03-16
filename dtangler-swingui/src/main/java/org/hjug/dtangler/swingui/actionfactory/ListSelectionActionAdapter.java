//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.actionfactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListSelectionActionAdapter implements ListSelectionListener {

	private final ActionListener listener;

	public ListSelectionActionAdapter(ActionListener listener) {
		this.listener = listener;
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		listener.actionPerformed(new ActionEvent(e.getSource(), 0, ""));
	}

}
