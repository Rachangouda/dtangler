//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.fileinput.impl;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.actionfactory.KeyActionAdapter;
import org.hjug.dtangler.swingui.resource.icons.IconKey;
import org.hjug.dtangler.swingui.windowmanager.SwingBaseView;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

public class SwingFileInputSelectorView extends SwingBaseView implements
		FileInputSelectorView {

	private final JComboBox<String> engineCombo = new JComboBox<>();
	private final JList<String> paths = new JList<>();
	private final JList<String> masks = new JList<>();
	private final JButton addPathBtn;
	private final JButton removePathBtn;
	private final JButton addMaskBtn;
	private final JButton removeMaskBtn;
	private final JButton okBtn;
	private final JButton cancelBtn;

	public SwingFileInputSelectorView(ActionFactory actionFactory) {
		super(actionFactory);
		addPathBtn = createButton("Add...", Actions.addPath, IconKey.plus12);
		removePathBtn = createButton("Remove", Actions.removePath,
				IconKey.minus12);
		addMaskBtn = createButton("Add...", Actions.addMask, IconKey.plus12);
		removeMaskBtn = createButton("Remove", Actions.removeMask,
				IconKey.minus12);
		okBtn = createButton("OK", Actions.ok);
		cancelBtn = createButton("Cancel", Actions.cancel);
		engineCombo.setName("engineCombo");
		engineCombo.addActionListener(actionFactory.getAction(Actions.selectEngine));
		paths.getSelectionModel().addListSelectionListener(
				createSelectionActionAdapter(Actions.updateActionStates));
		paths.addKeyListener(new KeyActionAdapter(KeyEvent.VK_INSERT,
				actionFactory.getAction(Actions.addPath)));
		paths.addKeyListener(new KeyActionAdapter(KeyEvent.VK_DELETE,
				actionFactory.getAction(Actions.removePath)));
		masks.getSelectionModel().addListSelectionListener(
				createSelectionActionAdapter(Actions.updateActionStates));
		masks.addKeyListener(new KeyActionAdapter(KeyEvent.VK_INSERT,
				actionFactory.getAction(Actions.addMask)));
		masks.addKeyListener(new KeyActionAdapter(KeyEvent.VK_DELETE,
				actionFactory.getAction(Actions.removeMask)));
		paths.setName("PATHS");
		masks.setName("MASKS");
		addCommonKeyEvent(getViewComponent(), KeyEvent.VK_ENTER, Actions.ok);
		addCommonKeyEvent(getViewComponent(), KeyEvent.VK_ESCAPE,
				Actions.cancel);
	}

	protected JComponent buildViewComponent() {
		DefaultFormBuilder builder = new DefaultFormBuilder(
				new FormLayout("fill:10dlu:grow",
						"p,1dlu,p,8dlu,p,fill:20dlu:grow,2dlu,p,8dlu,p,fill:20dlu:grow,2dlu,p,8dlu,p"));
		builder.setDefaultDialogBorder();
		builder.appendSeparator("Dependency engine");
		builder.nextRow();
		builder.append(engineCombo);
		builder.nextRow();
		builder.appendSeparator("Directories or files to search from");
		builder.append(new JScrollPane(paths));
		builder.nextRow();
		builder.append(ButtonBarFactory.buildLeftAlignedBar(addPathBtn,
				removePathBtn));
		builder.nextRow();
		builder.appendSeparator("Filename masks to ignore");
		builder.append(new JScrollPane(masks));
		builder.nextRow();
		builder.append(ButtonBarFactory.buildLeftAlignedBar(addMaskBtn,
				removeMaskBtn));
		builder.nextRow();
		builder.append(ButtonBarFactory.buildRightAlignedBar(okBtn, cancelBtn));
		return builder.getPanel();
	}

	public void setMasks(List<String> masks) {
		this.masks.setListData(new Vector<>(masks));
	}

	public void setEngines(List<String> engines) {
		this.engineCombo.removeAllItems();
		if (engines == null)
			return;
		for (String engine : engines)
			this.engineCombo.addItem(engine);
	}

	public void setEngineSelection(String engine) {
		engineCombo.setSelectedItem(engine);
	}

	public String getEngineSelection() {
		return (String)engineCombo.getSelectedItem();
	}

	public void setPaths(List<String> paths) {
		this.paths.setListData(new Vector<>(paths));
	}

	public List<String> getMaskSelection() {
		return masks.getSelectedValuesList();
	}

	public List<String> getPathSelection() {
		return paths.getSelectedValuesList();
	}

	public Dimension getPreferredSize() {
		return new Dimension(350, 400);
	}

	public String getTitle() {
		return "Select input data";
	}

}
