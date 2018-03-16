//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencyengine.DependencyEngineFactory;
import org.hjug.dtangler.core.input.ArgumentBuilder;
import org.hjug.dtangler.swingui.aboutinfodisplayer.AboutInfoDisplayer;
import org.hjug.dtangler.swingui.aboutinfodisplayer.impl.AboutInfoDisplayerImpl;
import org.hjug.dtangler.swingui.directoryselector.impl.SwingDirectorySelector;
import org.hjug.dtangler.swingui.dsm.impl.DsmViewFactoryImpl;
import org.hjug.dtangler.swingui.fileinput.impl.FileInputSelectorImpl;
import org.hjug.dtangler.swingui.fileselector.FileSelector;
import org.hjug.dtangler.swingui.fileselector.impl.SwingFileSelector;
import org.hjug.dtangler.swingui.groupselector.GroupSelector;
import org.hjug.dtangler.swingui.groupselector.impl.GroupSelectorImpl;
import org.hjug.dtangler.swingui.mainview.MainViewFactory;
import org.hjug.dtangler.swingui.mainview.impl.MainViewFactoryImpl;
import org.hjug.dtangler.swingui.rulememberselector.RuleMemberSelector;
import org.hjug.dtangler.swingui.rulememberselector.impl.RuleMemberSelectorImpl;
import org.hjug.dtangler.swingui.rulesselector.RulesSelector;
import org.hjug.dtangler.swingui.rulesselector.impl.RulesSelectorImpl;
import org.hjug.dtangler.swingui.textinput.impl.TextInputSelectorImpl;
import org.hjug.dtangler.swingui.windowmanager.DialogManager;
import org.hjug.dtangler.swingui.windowmanager.WindowManager;

public class ApplicationStarter {

	private final MainViewFactory viewFactory;

	public ApplicationStarter(WindowManager windowManager,
			DialogManager dialogManager) {
		viewFactory = createMainViewFactory(windowManager, dialogManager);
	}

	private MainViewFactory createMainViewFactory(WindowManager windowManager,
			DialogManager dialogManager) {
		DependencyEngineFactory dependencyEngineFactory = new DependencyEngineFactory();
		DsmViewFactoryImpl dsmViewFactory = new DsmViewFactoryImpl();
		SwingDirectorySelector swingDirectorySelector = new SwingDirectorySelector();
		TextInputSelectorImpl textInputSelector = new TextInputSelectorImpl(
				windowManager);
		FileInputSelectorImpl fileInputSelector = new FileInputSelectorImpl(
				swingDirectorySelector, textInputSelector, windowManager, dependencyEngineFactory);
		RuleMemberSelector ruleMemberSelector = new RuleMemberSelectorImpl(
				windowManager);
		GroupSelector groupSelector = new GroupSelectorImpl(windowManager,
				textInputSelector);
		RulesSelector rulesSelector = new RulesSelectorImpl(ruleMemberSelector,
				windowManager, groupSelector);
		AboutInfoDisplayer aboutInfoDisplayer = new AboutInfoDisplayerImpl(
				windowManager);
		FileSelector fileSelector = new SwingFileSelector();

		return new MainViewFactoryImpl(
				dsmViewFactory, fileInputSelector, rulesSelector, fileSelector,
				windowManager, aboutInfoDisplayer, dialogManager, dependencyEngineFactory);
	}

	public void start(String[] args) {
		Arguments arguments = new ArgumentBuilder().build(args);
		viewFactory.openMainView(arguments);
	}

}
