// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.ruleanalysis;

import org.hjug.dtangler.core.configuration.Group;
import org.hjug.dtangler.core.dependencies.Dependable;
import org.hjug.dtangler.core.util.WildcardMatch;

public class GroupRuleMember implements RuleMember {
	private Group group;

	public GroupRuleMember(Group group) {
		super();
		this.group = group;
	}

	public boolean appliesTo(Dependable dependable) {
		if (group.getGroupItems().contains(dependable.getFullyQualifiedName()))
			return true;

		for (String groupItem : group.getGroupItems()) {
			if (!isExcluded(dependable.getFullyQualifiedName())) {
				if (new WildcardMatch(groupItem).isMatch(dependable
						.getFullyQualifiedName()))
					return true;
			}
		}
		return false;
	}

	private boolean isExcluded(String dependable) {
		for (String excludedItem : group.getExcludedItems()) {
			if (new WildcardMatch(excludedItem).isMatch(dependable))
				return true;
			if (excludedItem.equals(dependable))
				return true;
		}
		return false;
	}

	public String getName() {
		return group.getName();
	}

	@Override
	public String toString() {
		return group.toString();
	}

	@Override
	public int hashCode() {
		return 31 + group.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GroupRuleMember))
			return false;
		final GroupRuleMember other = (GroupRuleMember) obj;
		return (group.equals(other.group));
	}
}
