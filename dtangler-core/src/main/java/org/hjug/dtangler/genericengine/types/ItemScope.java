// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.genericengine.types;

import org.hjug.dtangler.core.dependencies.Scope;
import org.hjug.dtangler.core.exception.DtException;

public class ItemScope implements Scope {

	private int index;
	private String scopeName;

	public ItemScope(String scopeName, int index) {
		if (scopeName == null)
			throw new DtException("invalid scope name: null");
		this.index = index;
		this.scopeName = scopeName;
	}

	public String getDisplayName() {
		return scopeName;
	}

	public int index() {
		return this.index;
	}

	public int hashCode() {
		return getDisplayName().hashCode();
	}

	public String toString() {
		return getDisplayName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ItemScope))
			return false;
		ItemScope other = (ItemScope) obj;
		return this.getDisplayName().equals(other.getDisplayName());
	}

}
