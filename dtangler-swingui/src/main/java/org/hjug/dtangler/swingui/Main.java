//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

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
