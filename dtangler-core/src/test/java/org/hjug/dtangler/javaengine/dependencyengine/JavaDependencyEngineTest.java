// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.javaengine.dependencyengine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencies.Dependable;
import org.hjug.dtangler.core.dependencies.Dependencies;
import org.hjug.dtangler.core.dependencies.DependencyGraph;
import org.hjug.dtangler.core.testutil.ClassPathEntryFinder;
import org.hjug.dtangler.javaengine.types.JavaScope;
import org.junit.Test;

public class JavaDependencyEngineTest {

	private final String path1 = ClassPathEntryFinder
			.getPathContaining("testdata-cyclic");
	private final String path2 = ClassPathEntryFinder
			.getPathContaining("testdata-good-deps");

	List<String> path = Collections.singletonList(path2);

	@Test
	public void testGetJarDependencies() {
		String path = ClassPathEntryFinder.getPathContaining("core")
				+ "/org/hjug/dtangler/core/acceptancetests/testdata/jarexample.jar";

		Arguments args = new Arguments();
		args.setInput(Collections.singletonList(path));

		JavaDependencyEngine engine = new JavaDependencyEngine();
		Dependencies jarDeps = engine.getDependencies(args);

		Set<Dependable> allItems = jarDeps.getAllItems();
		assertEquals("one jar location, one package, two class files", 4,
				allItems.size());
	}

	@Test
	public void testDefaultScopeDependencies() {
		Arguments defaultArgs = new Arguments();
		defaultArgs.setInput(path);

		testGetPackageDependencies(defaultArgs);
	}

	@Test
	public void testLocationScopeDependencies() {

		Arguments args = new Arguments();
		args.setInput(Arrays.asList(path1, path2));
		args.setScope(JavaScope.locations.getDisplayName());
		JavaDependencyEngine engine = new JavaDependencyEngine();
		DependencyGraph deps = engine.getDependencies(args).getDependencyGraph();
		List<String> items = getItemNames(deps.getAllItems());
		assertTrue(items.containsAll(Arrays.asList(path1, path2)));
	}

	@Test
	public void testPackageScopeDependencies() {
		Arguments packageArgs = new Arguments();
		packageArgs.setInput(path);
		packageArgs.setScope("packages");

		testGetPackageDependencies(packageArgs);
	}

	@Test
	public void testClassScopeDependencies() {
		Arguments classArgs = new Arguments();
		classArgs.setInput(path);
		classArgs.setScope("classes");

		JavaDependencyEngine engine = new JavaDependencyEngine();
		DependencyGraph deps = engine.getDependencies(classArgs)
				.getDependencyGraph();
		List<String> items = getItemNames(deps.getAllItems());

		assertEquals(6, items.size());
		assertTrue(items.containsAll(new HashSet<>(Arrays.asList(
				"MySecondClient", "MyClient", "MyService", "AnotherClass",
				"MyApi", "YetAnotherClass"))));
	}

	private void testGetPackageDependencies(Arguments args) {
		JavaDependencyEngine engine = new JavaDependencyEngine();
		DependencyGraph deps = engine.getDependencies(args).getDependencyGraph();

		List<String> items = getItemNames(deps.getAllItems());

		assertEquals(3, items.size());
		assertTrue(items.contains("eg.foo.good.deps.client"));
		assertTrue(items.contains("eg.foo.good.deps.impl"));
		assertTrue(items.contains("eg.foo.good.deps.api"));
	}

	private List<String> getItemNames(Set<Dependable> items) {
		List<String> names = new ArrayList<>();
		for (Dependable dep : items) {
			names.add(dep.getDisplayName());
		}
		return names;
	}
}