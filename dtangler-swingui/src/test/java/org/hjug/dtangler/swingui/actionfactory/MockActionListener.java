//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.actionfactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MockActionListener implements ActionListener {

	public int timesPerformed = 0;

	public void actionPerformed(ActionEvent e) {
		timesPerformed++;
	}

}
