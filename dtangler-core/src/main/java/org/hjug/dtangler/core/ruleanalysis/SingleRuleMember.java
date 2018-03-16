// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.ruleanalysis;

import org.hjug.dtangler.core.dependencies.Dependable;

public class SingleRuleMember implements RuleMember {

	private final String name;

	public SingleRuleMember(String name) {
		this.name = name;
	}

	public boolean appliesTo(Dependable dependable) {
		return name.equals(dependable.getDisplayName());
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SingleRuleMember))
			return false;
		final SingleRuleMember other = (SingleRuleMember) obj;
		return (name.equals(other.name));
	}

	@Override
	public int hashCode() {
		return 31 + name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}
}
