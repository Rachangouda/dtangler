//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.textinput.impl;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hjug.dtangler.swingui.actionfactory.ActionFactory;
import org.hjug.dtangler.swingui.windowmanager.SwingBaseView;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

class SwingTextInputView extends SwingBaseView implements TextInputView {

	private final JLabel fieldLabel = new JLabel();
	private final JTextField field = new JTextField();
	private final JButton okBtn;
	private final JButton cancelBtn;
	private String title;

	public SwingTextInputView(final ActionFactory actionFactory) {
		super(actionFactory);
		okBtn = createButton("OK", Actions.ok);
		cancelBtn = createButton("Cancel", Actions.cancel);
		field.getDocument().addDocumentListener(
				createDocumentChangeAdapter(Actions.updateActionStates));
		fieldLabel.setName("FIELDNAME");
		field.setName("VALUE");
		addCommonKeyEvent(getViewComponent(), KeyEvent.VK_ENTER, Actions.ok);
		addCommonKeyEvent(getViewComponent(), KeyEvent.VK_ESCAPE,
				Actions.cancel);
	}

	protected JComponent buildViewComponent() {
		DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(
				"fill:100dlu:grow", "10dlu,2dlu,p,4dlu,p"));
		builder.setDefaultDialogBorder();
		builder.append(fieldLabel);
		builder.nextRow();
		builder.append(field);
		builder.nextRow();
		builder.append(ButtonBarFactory.buildRightAlignedBar(okBtn, cancelBtn));
		return builder.getPanel();
	}

	public String getValue() {
		return field.getText();
	}

	public void setFieldName(String fieldName) {
		fieldLabel.setText(fieldName);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Dimension getPreferredSize() {
		return new Dimension(300, 130);
	}

	public String getTitle() {
		return title;
	}

}