//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.windowmanager;

public class UIExceptionHandlerDelegator {

	private static UIExceptionHandler handler;

	public static void setUIExceptionHandler(UIExceptionHandler handler) {
		UIExceptionHandlerDelegator.handler = handler;
	}

	public void handle(Throwable t) throws Throwable {
		if (handler != null)
			handler.handleUIException(t);
		else
			throw t;
	}
}
