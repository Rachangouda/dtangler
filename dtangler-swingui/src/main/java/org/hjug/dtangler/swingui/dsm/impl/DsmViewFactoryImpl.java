//This product is provided under the terms of EPL (Eclipse Public License) 
//version 1.0.
//
//The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php

package org.hjug.dtangler.swingui.dsm.impl;

import org.hjug.dtangler.swingui.dsm.DsmViewFactory;
import org.hjug.dtangler.swingui.dsm.SwingDsm;
import org.hjug.dtangler.ui.dsm.DsmGuiModel;
import org.hjug.dtangler.ui.dsm.impl.DsmGuiModelImpl;

public class DsmViewFactoryImpl implements DsmViewFactory {

	public SwingDsm createSwingDsm() {
		SwingDsmView view = new SwingDsmView();
		DsmGuiModel model = new DsmGuiModelImpl();
		new DsmPresenter(view, model);
		return new SwingDsm(view, model);
	}

}
