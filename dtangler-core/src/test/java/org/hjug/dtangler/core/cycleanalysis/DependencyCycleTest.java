// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.cycleanalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.hjug.dtangler.core.analysisresult.Violation;
import org.hjug.dtangler.core.dependencies.Dependable;
import org.hjug.dtangler.core.dependencies.TestDependable;
import org.junit.Test;

public class DependencyCycleTest {

	@Test
	public void testEqualsAndHashCode() {
		DependencyCycle same1 = new TestDependencyCycle(Arrays.asList("foo",
				"bar", "foo"));
		DependencyCycle same2 = new TestDependencyCycle(Arrays.asList("foo",
				"bar", "foo"));
		DependencyCycle different = new TestDependencyCycle(Arrays.asList(
				"bay", "bar", "bay"));

		assertEquals(same1, same2);
		assertEquals(same1.hashCode(), same2.hashCode());

		assertFalse(same1.equals(different));
		assertFalse(same1.equals(null));
		assertFalse(same1.equals(Boolean.TRUE));
	}

	@Test
	public void testAppliesTo() {
		Dependable depA = new TestDependable("a");
		Dependable depB = new TestDependable("b");
		Dependable depC = new TestDependable("c");

		Violation v = new DependencyCycle(Arrays.asList(depA, depB));

		assertTrue(v.appliesTo(new HashSet<>(Arrays.asList(depA, depB, depC))));
		assertTrue(v.appliesTo(new HashSet<>(Arrays.asList(depA, depB))));
		assertFalse(v.appliesTo(new HashSet<>(Arrays.asList(depA, depC))));
		assertFalse(v.appliesTo(new HashSet<>(Collections.singletonList(depA))));
		assertFalse(v.appliesTo(new HashSet<>(Collections.singletonList(depB))));
		assertFalse(v.appliesTo(new HashSet<>(Collections.singletonList(depC))));
	}
}
