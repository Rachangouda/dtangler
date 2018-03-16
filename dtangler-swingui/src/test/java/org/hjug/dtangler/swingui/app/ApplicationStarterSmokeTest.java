//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.app;

import static org.junit.Assert.assertNotNull;

import org.hjug.dtangler.swingui.ApplicationStarter;
import org.hjug.dtangler.swingui.windowmanager.MockDialogManager;
import org.hjug.dtangler.swingui.windowmanager.MockWindowManager;
import org.junit.Test;

public class ApplicationStarterSmokeTest {

	@Test
	public void testStartup() {
		MockWindowManager windowManager = new MockWindowManager();
		MockDialogManager dialogManager = new MockDialogManager();

		new ApplicationStarter(windowManager, dialogManager)
				.start(new String[] { "_build" });
		assertNotNull(windowManager.getLastShownView());
	}
}
