package oscilloscope;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import resources.JSONResource;
import resources.Resource;

/**
 * @fbielejec
 */

public class Oscilloscope {

	// http://www.nikgrozev.org/2014/10/16/rest-with-embedded-jetty-and-jersey-in-a-single-jar-step-by-step/

	public static void main(String[] args) {

		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
 
        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);
 
        ServletHolder jerseyServlet = context.addServlet(
             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
 
        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
           "jersey.config.server.provider.classnames",
           JSONResource.class.getCanonicalName());
		
//		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//		context.setContextPath("/");
//
//		Server jettyServer = new Server(8080);
//		jettyServer.setHandler(context);
//
////		ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
////		jerseyServlet.setInitOrder(0);
////
////		// Tells the Jersey Servlet which REST service/class to load.
////		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", Resource.class.getCanonicalName());

		try {

			jettyServer.start();
			jettyServer.join();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			jettyServer.destroy();
		}

	}

}
