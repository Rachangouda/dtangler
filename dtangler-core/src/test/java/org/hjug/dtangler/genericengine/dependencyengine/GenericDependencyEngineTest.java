// This product is provided under the terms of EPL (Eclipse Public License) 
// version 1.0.
//
// The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php 

package org.hjug.dtangler.genericengine.dependencyengine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencies.Dependable;
import org.hjug.dtangler.core.dependencies.Dependencies;
import org.hjug.dtangler.core.dependencies.DependencyGraph;
import org.hjug.dtangler.core.testutil.ClassPathEntryFinder;
import org.junit.Test;

public class GenericDependencyEngineTest {

	@Test
	public void testDependencies() {

		String corePath = ClassPathEntryFinder.getPathContaining("core");
		String dtPath = corePath
				+ "/org/hjug/dtangler/genericengine/dependencyengine/testdata/test.dt";
		Arguments args = new Arguments();
		args.setInput(Collections.singletonList(dtPath));
		GenericDependencyEngine engine = new GenericDependencyEngine();
		Dependencies dependencies = engine.getDependencies(args);
		DependencyGraph deps = dependencies.getDependencyGraph(
				dependencies.getDefaultScope());
		assertEquals("scope2", dependencies.getDefaultScope().getDisplayName());
		List<String> items = getItemNames(deps.getAllItems());
		assertEquals(2, items.size());
		assertTrue(items.containsAll(new HashSet<String>(Arrays
				.asList("item1", "item2"))));
	}

	private List<String> getItemNames(Set<Dependable> items) {
		List<String> names = new ArrayList<String>();
		for (Dependable dep : items) {
			names.add(dep.getDisplayName());
		}
		return names;
	}
}