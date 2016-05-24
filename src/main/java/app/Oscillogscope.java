package app;

import static spark.Spark.get;
import static spark.Spark.setPort;
import static spark.Spark.setIpAddress;
import static spark.Spark.staticFileLocation;

import resources.Test;
import resources.TestResource;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @fbielejec
 */

public class Oscillogscope {

	private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null
			? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
			
	private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null
			? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

	public static void main(String[] args) {

		setIpAddress(IP_ADDRESS);
		setPort(PORT);

		// ---STATIC RESOURCES---//

		staticFileLocation("/webapp");
//		 get("/", (request, response) -> "Hello World");
		
		// ---REST API ---//
		
		new TestResource(new Test());

	}// END: main
}
