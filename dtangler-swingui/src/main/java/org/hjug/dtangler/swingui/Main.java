//This product is provided under the terms of EPL (Eclipse Public License) 
//version 1.0.
//
//The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php

package org.hjug.dtangler.swingui;

import org.hjug.dtangler.swingui.windowmanager.UIExceptionHandlerDelegator;
import org.hjug.dtangler.swingui.windowmanager.impl.SwingWindowManager;

public class Main {
	public static void main(String[] args) {
			String handlerClassName = UIExceptionHandlerDelegator.class
					.getName();
			System.setProperty("sun.awt.exception.handler", handlerClassName);

			SwingWindowManager windowManager = new SwingWindowManager();
			UIExceptionHandlerDelegator.setUIExceptionHandler(windowManager);
			new ApplicationStarter(windowManager, windowManager).start(args);
	}
}
