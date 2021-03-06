// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.ruleanalysis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.hjug.dtangler.core.configuration.Group;
import org.hjug.dtangler.core.dependencies.TestDependable;
import org.junit.Test;

public class GroupRuleMemberTest {

	@Test
	public void testAppliesTo() {

		String excludedPackages = "org.public.*";
		String excludedSubpackage = "org.public.stats";

		String containedPackages = "org.*";
		String containedSubpackage = "org.db";
		String otherPackage = "test.util";

		Set<String> included = new HashSet<>();
		included.add(containedPackages);

		Set<String> excluded = new HashSet<>();
		excluded.add(excludedPackages);

		Group a = new Group("A", included, excluded);
		RuleMember member = new GroupRuleMember(a);

		assertTrue(member.appliesTo(new TestDependable(containedSubpackage)));
		assertFalse(member.appliesTo(new TestDependable(otherPackage)));
		assertFalse(member.appliesTo(new TestDependable(excludedSubpackage)));
		assertFalse(member.appliesTo((new TestDependable(excludedPackages))));

	}

}
