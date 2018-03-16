//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.mainview.impl;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencyengine.DependencyEngineFactory;
import org.hjug.dtangler.swingui.aboutinfodisplayer.AboutInfoDisplayer;
import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.dsm.DsmViewFactory;
import org.hjug.dtangler.swingui.dsm.SwingDsm;
import org.hjug.dtangler.swingui.fileinput.FileInputSelector;
import org.hjug.dtangler.swingui.fileselector.FileSelector;
import org.hjug.dtangler.swingui.mainview.MainViewFactory;
import org.hjug.dtangler.swingui.rulesselector.RulesSelector;
import org.hjug.dtangler.swingui.windowmanager.DialogManager;
import org.hjug.dtangler.swingui.windowmanager.WindowManager;

public class MainViewFactoryImpl implements MainViewFactory {

	private final DsmViewFactory dsmViewFactory;
	private final FileInputSelector inputSelector;
	private final WindowManager windowManager;
	private final RulesSelector rulesSelector;
	private final AboutInfoDisplayer aboutInfoDisplayer;
	private final FileSelector fileSelector;
	private final DialogManager dialogManager;
	private final DependencyEngineFactory dependencyEngineFactory;
	
	public MainViewFactoryImpl(DsmViewFactory dsmViewFactory,
			FileInputSelector inputSelector, RulesSelector rulesSelector,
			FileSelector fileSelector, WindowManager windowManager,
			AboutInfoDisplayer aboutInfoDisplayer, DialogManager dialogManager,
			DependencyEngineFactory dependencyEngineFactory) {
		this.dsmViewFactory = dsmViewFactory;
		this.rulesSelector = rulesSelector;
		this.inputSelector = inputSelector;
		this.fileSelector = fileSelector;
		this.windowManager = windowManager;
		this.aboutInfoDisplayer = aboutInfoDisplayer;
		this.dialogManager = dialogManager;
		this.dependencyEngineFactory = dependencyEngineFactory;
	}

	public void openMainView(Arguments arguments) {
		ActionFactory actionfactory = new ActionFactory();
		SwingDsm swingDsm = dsmViewFactory.createSwingDsm();
		SwingMainView view = new SwingMainView(actionfactory, swingDsm
				.getView());
		ConfigurationModel configModel = new ConfigurationModel(fileSelector,
				dialogManager, arguments);
		MainViewModel model = new DefaultMainViewModel(swingDsm.getModel(),
				configModel, inputSelector, rulesSelector,
				dependencyEngineFactory);
		MainViewPresenter presenter = new MainViewPresenter(model, view);
		new MainViewGlue(actionfactory, presenter, model, windowManager, view,
				aboutInfoDisplayer);
		windowManager.showMainView(view);
	}

}
