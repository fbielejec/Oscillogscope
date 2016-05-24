package app;

import resources.Test;
import resources.TestResource;
import spark.Spark;

/**
 * @fbielejec
 */

public class Oscillogscope {

	public static void main(String[] args) {

		Spark.setPort(getHerokuAssignedPort());

		// ---STATIC RESOURCES---//

		Spark.staticFileLocation("/webapp");

		// ---REST API ---//

		new TestResource(new Test());

	}// END: main

	static int getHerokuAssignedPort() {
		/**
		 * @return default port if heroku-port isn't set (i.e. on localhost)
		 */
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 8080;
	}

}
