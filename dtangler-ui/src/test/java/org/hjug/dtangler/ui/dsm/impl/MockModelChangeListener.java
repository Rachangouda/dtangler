//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.ui.dsm.impl;

import org.hjug.dtangler.ui.dsm.DsmGuiModelChangeListener;

public class MockModelChangeListener implements DsmGuiModelChangeListener {

	public int timesGuiModelChangedCalled = 0;
	public int timesDataChangedCalled = 0;

	public void dsmGuiModelChanged() {
		timesGuiModelChangedCalled++;
	}

	public void dsmDataChanged() {
		timesDataChangedCalled++;
	}

}
