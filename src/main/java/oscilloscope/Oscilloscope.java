package oscilloscope;

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

public class Oscilloscope {

	private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null
			? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
			
	private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null
			? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

	public static void main(String[] args) {

		setIpAddress(IP_ADDRESS);
		setPort(PORT);
		staticFileLocation("/webapp");

		// ---REST API ---//
		
		new TestResource(new Test());
		
		// ---STATIC RESOURCES---//

		get("/", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				return "Hello World!!";
			}
		});

	}// END: main
}
