//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.swingui.windowmanager;

public class MockWindowManager implements WindowManager,
		WindowInteractionProvider {

	private SwingView lastShownView;
	private Runnable testCodeForNextModal;

	public void close(SwingView view) {
		if (view == lastShownView)
			lastShownView = null;
	}

	public void showModal(SwingView view) {
		lastShownView = view;
		if (testCodeForNextModal != null)
			testCodeForNextModal.run();
		testCodeForNextModal = null;
		view.setWindowInteractionProvider(this);
	}

	public SwingView getLastShownView() {
		return lastShownView;
	}

	public void setTestCodeForNextModal(Runnable runnable) {
		this.testCodeForNextModal = runnable;
	}

	public void showMainView(SwingView view) {
		lastShownView = view;
		view.setWindowInteractionProvider(this);
	}

	public void updateTitle(String newTitle) {
	}

}
