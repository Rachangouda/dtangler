//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.dsm.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import javax.swing.JLabel;

import org.hjug.dtangler.ui.dsm.MockDependableInfo;
import org.junit.Test;

public class DependableInfoRowTableCellRendererTest {

	private JLabel getRenderedComponent(Object value) {
		return (JLabel) new DependableInfoRowTableCellRenderer()
				.getTableCellRendererComponent(null, value, false, false, 0, 0);
	}

	@Test
	public void testRenderDependableInfo() {
		JLabel c = getRenderedComponent(new MockDependableInfo("FooBar", 2, 10,
				false));
		assertEquals(Color.lightGray, c.getBackground());
		assertTrue(c.isOpaque());
		assertEquals("  2 FooBar (10)", c.getText());

		c = getRenderedComponent(new MockDependableInfo("FooBar", 14, 8, true));
		assertEquals(Color.gray, c.getBackground());
		assertTrue(c.isOpaque());
		assertEquals(" 14 FooBar (8)", c.getText());

	}

}
