// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.filefinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class RecursiveFileFinderTest {

	private String getTestDataPath() {
		try {
			return new File(getClass().getResource("testdata").toURI())
					.getAbsolutePath();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetFilesWithPaths() {
		RecursiveFileFinder fileFinder = new RecursiveFileFinder();
		fileFinder.setFilter(new FullPathWildCardFileFilter(
				Collections.singletonList(".class"), Collections.emptyList()));
		String path1 = getPath("testdata");
		fileFinder.findFiles(path1);
		Map<File, String> actual = fileFinder.getFilesWithPaths();
		Collection<String> values = actual.values();
		Set<String> paths = new HashSet<>(values);

		assertEquals(1, paths.size());
		assertTrue(paths.iterator().next().endsWith("testdata"));
	}

	private String getPath(String from) {
		String path = getClass().getResource(from).getFile();
		if (path.contains("%20"))
			path = path.replaceAll("%20", " ");
		return path;
	}

	@Test
	public void testFindFilesWithoutFilter() {
		RecursiveFileFinder fileFinder = new RecursiveFileFinder();
		fileFinder.findFiles(getTestDataPath());
		assertEquals(6, fileFinder.getFiles().size());
		assertTrue(fileFinder.getFiles().contains(
				new File(getTestDataPath() + "/Foo.class")));
		assertTrue(fileFinder.getFiles().contains(
				new File(getTestDataPath() + "/Text.txt")));
	}

	@Test
	public void testFindClassFilesWithFilter() {
		RecursiveFileFinder fileFinder = new RecursiveFileFinder();
		fileFinder.setFilter(pathname -> pathname.getName().endsWith(".class"));

		fileFinder.findFiles(getTestDataPath());
		assertEquals(4, fileFinder.getFiles().size());
		assertTrue(fileFinder.getFiles().contains(
				new File(getTestDataPath() + "/Foo.class")));
		assertFalse(fileFinder.getFiles().contains(
				new File(getTestDataPath() + "/Text.txt")));
	}
}
