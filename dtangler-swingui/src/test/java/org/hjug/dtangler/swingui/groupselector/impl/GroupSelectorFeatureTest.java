// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.groupselector.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.hjug.dtangler.core.configuration.Group;
import org.hjug.dtangler.swingui.textinput.MockTextInputSelector;
import org.hjug.dtangler.swingui.windowmanager.MockWindowManager;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.Button;
import org.uispec4j.ListBox;

public class GroupSelectorFeatureTest {

	private MockWindowManager windowManager;
	private GroupSelectorImpl selector;
	private MockTextInputSelector textInputSelector;

	@Before
	public void setUp() {
		windowManager = new MockWindowManager();
		textInputSelector = new MockTextInputSelector();
		selector = new GroupSelectorImpl(windowManager, textInputSelector);
	}

	@Test
	public void testCancelCreateNew() {
		windowManager.setTestCodeForNextModal(new Runnable() {
			public void run() {
				GroupSelectorDriver view = new GroupSelectorDriver(
						windowManager.getLastShownView());
				view.cancelButton.click();
			}
		});
		assertNull(selector.createGroup());
		assertNull(windowManager.getLastShownView());
	}

	@Test
	public void testOkOnCreateNew() {
		createTestGroupSelectorDriver();
		Group result = selector.createGroup();
		assertEquals("Foo", result.getName());
		assertTrue(result.getGroupItems().contains("xx"));
		assertTrue(result.getExcludedItems().contains("yy"));
		assertNull(windowManager.getLastShownView());
	}

	@Test
	public void testCancelEdit() {
		Group groupToEdit = new Group("myGroup", Collections.emptySet());

		windowManager.setTestCodeForNextModal(() -> {
            GroupSelectorDriver view = new GroupSelectorDriver(
                    windowManager.getLastShownView());
            view.cancelButton.click();
        });
		assertNull(selector.editGroup(groupToEdit));
		assertNull(windowManager.getLastShownView());
	}

	@Test
	public void testOkOnEdit() {
		Group groupToEdit = new Group("myGroup", Collections.emptySet());

		createTestGroupSelectorDriver();
		Group result = selector.editGroup(groupToEdit);
		assertNotSame(groupToEdit, result);
		assertEquals("Foo", result.getName());
		assertTrue(result.getGroupItems().contains("xx"));
		assertTrue(result.getExcludedItems().contains("yy"));
		assertNull(windowManager.getLastShownView());
	}

	private void createTestGroupSelectorDriver() {
		windowManager.setTestCodeForNextModal(() -> {
            GroupSelectorDriver view = new GroupSelectorDriver(
                    windowManager.getLastShownView());
            view.name.setText("Foo");
            textInputSelector.setNextValue("xx");
            view.addItemButton.click();
            textInputSelector.setNextValue("yy");
            view.addExcludedItemButton.click();
            view.okButton.click();
        });
	}

	@Test
	public void testEditShowsAllOriginalProperties() {
		Group groupToEdit = new Group("myGroup", new HashSet<>(Arrays.asList(
				"foo", "bar")), new HashSet<>(Arrays.asList("fooEx", "barEx")));

		selector.editGroup(groupToEdit);
		GroupSelectorDriver view = new GroupSelectorDriver(windowManager
				.getLastShownView());
		assertTrue(view.items.contentEquals(new String[] { "bar", "foo" })
				.isTrue());
		assertTrue(view.excludedItems.contentEquals(
				new String[] { "barEx", "fooEx" }).isTrue());
	}

	@Test
	public void testAddAndRemoveItems() {
		Group groupToEdit = new Group("myGroup", new HashSet<>(Arrays.asList(
				"foo", "bar")), Collections.emptySet());
		selector.editGroup(groupToEdit);
		GroupSelectorDriver view = new GroupSelectorDriver(windowManager
				.getLastShownView());

		testAddAndRemoveItems(view.items, view.addItemButton,
				view.removeItemsButton);
	}

	@Test
	public void testAddAndRemoveExcludedItems() {
		Group groupToEdit = new Group("myGroup", Collections.emptySet(),
				new HashSet<>(Arrays.asList("foo", "bar")));
		selector.editGroup(groupToEdit);
		GroupSelectorDriver view = new GroupSelectorDriver(windowManager
				.getLastShownView());

		testAddAndRemoveItems(view.excludedItems, view.addExcludedItemButton,
				view.removeExcludedItemsButton);
	}

	private void testAddAndRemoveItems(ListBox list, Button addBtn,
			Button removeBtn) {
		Group groupToEdit = new Group("myGroup", new HashSet<>(Arrays.asList(
				"foo", "bar")), Collections.emptySet());
		selector.editGroup(groupToEdit);

		textInputSelector.setNextValue("bay");
		addBtn.click();

		assertTrue(list.contentEquals(new String[] { "bar", "bay", "foo" })
				.isTrue());

		list.selectIndices(new int[] { 0, 2 });
		removeBtn.click();

		assertTrue(list.contentEquals(new String[] { "bay" }).isTrue());
		textInputSelector.setNextValue(null);
		assertTrue(list.contentEquals(new String[] { "bay" }).isTrue());
		textInputSelector.setNextValue("bay");
		assertTrue(list.contentEquals(new String[] { "bay" }).isTrue());
	}

	@Test
	public void testRemoveItemButtonsAreOnlyEnabledWhenOneOrMoreItemsSelected() {
		Group groupToEdit = new Group("myGroup", new HashSet<>(Arrays.asList(
				"foo", "bar")), new HashSet<>(Arrays.asList("foo", "bar")));
		selector.editGroup(groupToEdit);
		GroupSelectorDriver view = new GroupSelectorDriver(windowManager
				.getLastShownView());

		testRemoveButtonIsOnlyEnabledWhenOneOrMoreItemsSelected(view.items,
				view.removeItemsButton);
		testRemoveButtonIsOnlyEnabledWhenOneOrMoreItemsSelected(
				view.excludedItems, view.removeExcludedItemsButton);
	}

	private void testRemoveButtonIsOnlyEnabledWhenOneOrMoreItemsSelected(
			ListBox listBox, Button button) {
		listBox.selectIndex(-1);
		assertFalse(button.isEnabled().isTrue());
		listBox.selectIndex(0);
		assertTrue(button.isEnabled().isTrue());
		listBox.selectIndices(new int[] { 0, 1 });
		assertTrue(button.isEnabled().isTrue());
	}

	@Test
	public void testOkIsOnlyEnabledIfGroupHasNameAndOneOrMoreItems() {
		selector.createGroup();
		GroupSelectorDriver view = new GroupSelectorDriver(windowManager
				.getLastShownView());
		assertFalse(view.okButton.isEnabled().isTrue());

		view.name.setText("aa");
		assertFalse(view.okButton.isEnabled().isTrue());

		textInputSelector.setNextValue("xx");
		view.addItemButton.click();
		assertTrue(view.okButton.isEnabled().isTrue());

		textInputSelector.setNextValue("yy");
		view.addItemButton.click();

		assertTrue(view.okButton.isEnabled().isTrue());

		view.name.setText("");
		assertFalse(view.okButton.isEnabled().isTrue());
	}

}
