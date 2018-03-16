// This product is provided under the terms of EPL (Eclipse Public License) 
// version 2.0.
//
// The full license text can be read from: https://www.eclipse.org/legal/epl-2.0/

package eg.foo.good.deps.client;

import eg.foo.good.deps.api.MyApi;

public class MyClient {
	public static final AnotherClass a = new AnotherClass();
	MyApi api;

	public void doSomething() {
		new YetAnotherClass().toString();
	}
}
