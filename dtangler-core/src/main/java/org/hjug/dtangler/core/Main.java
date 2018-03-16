//This product is provided under the terms of EPL (Eclipse Public License) 
//version 2.0.
//
//The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package org.hjug.dtangler.core;

import java.io.PrintStream;

import org.hjug.dtangler.core.exception.DtException;
import org.hjug.dtangler.core.textui.SysoutWriter;
import org.hjug.dtangler.core.versioninfo.VersionInfo;

public class Main {

	private static final int EXITCODE_SUCCESS = 0;
	private static final int EXITCODE_VIOLATIONS = -1;
	static final int EXITCODE_PROBLEM = -2;

	public static void main(String[] args) {
		int exitCode = run(args, System.out, System.err);
		System.exit(exitCode);
	}

	public static int run(String[] args, PrintStream out, PrintStream errorOut) {
		try {
			printVersionInfo(out);
			CommandLineApp app = new CommandLineApp(new SysoutWriter());
			boolean analysisOk = app.run(args);
			if (analysisOk)
				return EXITCODE_SUCCESS;
			else
				return EXITCODE_VIOLATIONS;
		} catch (MissingArgumentsException e) {
			errorOut.print(HelpText.helpText);
		} catch (DtException e) {
			errorOut.println("Error: " + e.getMessage());
		} catch (Exception e) {
			errorOut.println("Internal error: " + e.getMessage());
			e.printStackTrace(errorOut);
		}
		return EXITCODE_PROBLEM;
	}

	private static void printVersionInfo(PrintStream out) {
		out.println("dtangler " + VersionInfo.getVersionInfo()
				+ " (c) 2008 by contributors.");
		out
				.println("check www.dtangler.org for new versions and additional information");
	}
}