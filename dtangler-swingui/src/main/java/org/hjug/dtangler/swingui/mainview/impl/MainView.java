//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.mainview.impl;

import java.util.List;

import org.hjug.dtangler.core.dependencies.Scope;
import org.hjug.dtangler.swingui.actionfactory.ActionKey;

public interface MainView {

	enum Actions implements ActionKey {
		input, refresh, rules, exit, about, open, save, saveas, addforbiddendeps, clear, changeScope, zoomIn, zoomInContents, zoomInDependencies, zoomOut, toggleShortName
	}

	void setSelectionViolations(List<String> violations);

	void setAllViolations(List<String> violations);

	void setFileName(String fileName);

	void setScopes(List<? extends Scope> scopes);

	void setScope(Scope scope);

	Scope getSelectedScope();

	void setShortNameEnabled(boolean b);

	void addFileListDropListener(FileListDropListener l);

}
