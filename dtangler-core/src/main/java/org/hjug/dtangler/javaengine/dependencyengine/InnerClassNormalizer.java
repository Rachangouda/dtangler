// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.javaengine.dependencyengine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hjug.dtangler.javaengine.types.JavaClass;

public class InnerClassNormalizer {

	public Set<JavaClass> normalize(Set<JavaClass> classes) {

		Map<String, Set<JavaClass>> allInnerClasses = getInnerClasses(classes);

		Set<JavaClass> result = new HashSet<>();
		for (JavaClass clazz : classes) {
			if (clazz.isInnerClass())
				continue;

			Set<JavaClass> innerClasses = allInnerClasses.get(clazz
					.getBaseClassName());
			if (innerClasses != null)
				clazz.addInnerClasses(innerClasses);
			result.add(clazz);
		}
		return result;
	}

	private Map<String, Set<JavaClass>> getInnerClasses(Set<JavaClass> classes) {
		Map<String, Set<JavaClass>> result = new HashMap<>();
		for (JavaClass clazz : classes) {
			if (!clazz.isInnerClass())
				continue;
			Set<JavaClass> innerClasses = result.computeIfAbsent(clazz.getBaseClassName(), k -> new HashSet<>());
			innerClasses.add(clazz);
		}
		return result;

	}
}
