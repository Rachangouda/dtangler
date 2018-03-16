// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.testutil.ruleanalysis;

import org.hjug.dtangler.core.dependencies.Dependency;
import org.hjug.dtangler.core.dependencies.TestDependable;
import org.hjug.dtangler.core.ruleanalysis.Rule;
import org.hjug.dtangler.core.ruleanalysis.RuleViolation;

public class MockRuleViolation extends RuleViolation {
	public MockRuleViolation(String dependant, String dependee, Rule rule) {
		super(new Dependency(new TestDependable(dependant), new TestDependable(
				dependee)), rule);
	}
}
