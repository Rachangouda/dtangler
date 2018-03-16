//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.resource.icons;

import java.net.URL;

import javax.swing.ImageIcon;

public class IconProvider {

	public static ImageIcon getIcon(IconKey iconKey) {
		URL iconFile = IconProvider.class
				.getResource("/org/hjug/dtangler/swingui/resource/icons/"
						+ iconKey.name() + ".png");
		if (iconFile == null)
			return null;
		return new ImageIcon(iconFile);
	}

}
