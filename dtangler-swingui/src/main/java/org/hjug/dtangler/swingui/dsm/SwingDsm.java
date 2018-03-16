//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.dsm;

import org.hjug.dtangler.ui.dsm.DsmGuiModel;

public class SwingDsm {

	private final DsmView dsmView;
	private final DsmGuiModel model;

	public SwingDsm(DsmView dsmView, DsmGuiModel model) {
		this.dsmView = dsmView;
		this.model = model;
	}

	public final DsmView getView() {
		return dsmView;
	}

	public final DsmGuiModel getModel() {
		return model;
	}

}
