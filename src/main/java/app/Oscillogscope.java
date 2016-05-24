package app;

import resources.Test;
import resources.TestResource;
import spark.Spark;

/**
 * @fbielejec
 */

public class Oscillogscope {

//	private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null
//			? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
//			
//	private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null
//			? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

	public static void main(String[] args) {

//		Spark.setIpAddress(IP_ADDRESS);
//		Spark.setPort(PORT);
		Spark.setPort(getHerokuAssignedPort());
		
		// ---STATIC RESOURCES---//

		Spark.staticFileLocation("/webapp");
//		 get("/", (request, response) -> "Hello World");
		
		// ---REST API ---//
		
		new TestResource(new Test());

	}// END: main
	
    static int getHerokuAssignedPort() {
/**
 * @return default port if heroku-port isn't set (i.e. on localhost)
 * */
    	ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; 
    }
	
}
