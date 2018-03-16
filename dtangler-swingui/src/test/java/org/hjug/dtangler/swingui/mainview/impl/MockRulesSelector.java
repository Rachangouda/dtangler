//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.mainview.impl;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.swingui.rulesselector.RulesSelector;

public class MockRulesSelector implements RulesSelector {

	private boolean rulesSelected;
	private Arguments previousArguments;

	public Arguments selectRules(Arguments previousArguments) {
		this.previousArguments = previousArguments;
		rulesSelected = true;
		return null;
	}

	public boolean wereRulesSelected() {
		return rulesSelected;
	}

	public Arguments getPreviousRules() {
		return previousArguments;
	}

}
