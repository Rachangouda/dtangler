//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core.analysis.configurableanalyzer;

import org.hjug.dtangler.core.analysis.CompositeAnalyzer;
import org.hjug.dtangler.core.analysis.DependencyAnalyzer;
import org.hjug.dtangler.core.analysisresult.AnalysisResult;
import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.cycleanalysis.CycleValidator;
import org.hjug.dtangler.core.dependencies.Dependencies;
import org.hjug.dtangler.core.ruleanalysis.ForbiddenDependencyFinder;
import org.hjug.dtangler.core.ruleanalysis.RuleCreator;

public class ConfigurableDependencyAnalyzer {

	private DependencyAnalyzer analyzer;

	public ConfigurableDependencyAnalyzer(Arguments arguments) {
		analyzer = buildAnalyzer(arguments);
	}

	private DependencyAnalyzer buildAnalyzer(Arguments args) {
		CompositeAnalyzer analyzer = new CompositeAnalyzer();
		analyzer.add(new CycleValidator(args.getCyclesAllowed()));
		analyzer.add(new ForbiddenDependencyFinder(new RuleCreator(args
				.getForbiddenDependencies(), args.getAllowedDependencies(),
				args.getGroups()).createRules()));
		return analyzer;
	}

	public AnalysisResult analyze(Dependencies dependencies) {
		analyzer.analyze(dependencies);
		return new AnalysisResult(analyzer
				.getViolations(), analyzer.getChildViolations(), analyzer
				.isValidResult());
	}
}