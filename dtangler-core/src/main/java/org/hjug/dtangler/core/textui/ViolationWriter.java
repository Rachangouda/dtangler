// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.textui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hjug.dtangler.core.analysisresult.Violation;

public class ViolationWriter {
	private final Writer writer;

	public ViolationWriter(Writer writer) {
		this.writer = writer;
	}

	public void printViolations(Set<Violation> violations) {
		if (violations.isEmpty())
			return;
		List<String> sortedViolations = sortViolationsAlphabetically(violations);
		println("");
		for (String v : sortedViolations) {
			println(v);
		}
	}

	private List<String> sortViolationsAlphabetically(Set<Violation> violations) {
		List<String> sortedViolations = new ArrayList<>();
		for (Violation v : violations) {
			sortedViolations.add(v.asText());
		}
		Collections.sort(sortedViolations);
		return sortedViolations;
	}

	private void println(String s) {
		writer.println(s);
	}
}
