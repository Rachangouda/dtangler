//This product is provided under the terms of EPL (Eclipse Public License) 
//version 1.0.
//
//The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php

package org.hjug.dtangler.core;

import org.hjug.dtangler.core.analysis.configurableanalyzer.ConfigurableDependencyAnalyzer;
import org.hjug.dtangler.core.analysisresult.AnalysisResult;
import org.hjug.dtangler.core.configuration.Arguments;
import org.hjug.dtangler.core.dependencies.Dependencies;
import org.hjug.dtangler.core.dependencies.DependencyGraph;
import org.hjug.dtangler.core.dependencyengine.DependencyEngine;
import org.hjug.dtangler.core.dependencyengine.DependencyEngineFactory;
import org.hjug.dtangler.core.dsmengine.DsmEngine;
import org.hjug.dtangler.core.input.ArgumentBuilder;
import org.hjug.dtangler.core.textui.DSMWriter;
import org.hjug.dtangler.core.textui.ViolationWriter;
import org.hjug.dtangler.core.textui.Writer;

public class CommandLineApp {

	private final Writer writer;

	public CommandLineApp(Writer writer) {
		this.writer = writer;
	}

	public boolean run(String[] args) {
		if (args.length == 0)
			throw new MissingArgumentsException();
		return run(new ArgumentBuilder().build(args));
	}

	public boolean run(Arguments arguments) {
		DependencyEngine engine = new DependencyEngineFactory()
				.getDependencyEngine(arguments);
		Dependencies dependencies = engine.getDependencies(arguments);
		DependencyGraph dependencyGraph = dependencies
				.getDependencyGraph();
		AnalysisResult analysisResult = getAnalysisResult(arguments,
				dependencies);

		printDsm(dependencyGraph, analysisResult);
		return analysisResult.isValid();
	}

	private AnalysisResult getAnalysisResult(Arguments arguments,
			Dependencies dependencies) {
		return new ConfigurableDependencyAnalyzer(arguments)
				.analyze(dependencies);
	}

	private void printDsm(DependencyGraph dependencies,
			AnalysisResult analysisResult) {
		DSMWriter textUI = new DSMWriter(writer);
		textUI
				.printDsm(new DsmEngine(dependencies).createDsm(),
						analysisResult);
		ViolationWriter violationWriter = new ViolationWriter(writer);
		violationWriter.printViolations(analysisResult
				.getViolations(dependencies.getAllItems()));
	}
}
