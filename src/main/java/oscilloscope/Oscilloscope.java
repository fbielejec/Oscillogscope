package oscilloscope;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import resources.JSONResource;
import resources.Resource;

/**
 * @fbielejec
 */


//ServletContextHandler sch = new ServletContextHandler();
//sch.setContextPath("/xxx");
//
//resource = new TheResource();
//ResourceConfig rc = new ResourceConfig();
//rc.register(resource);
//
//ServletContainer sc = new ServletContainer(rc);        
//ServletHolder holder = new ServletHolder(sc);
//sch.addServlet(holder, "/*");
//
//Server server = new Server(port);
//server.setHandler(sch);
//server.start();
//server.join();

public class Oscilloscope {

	// http://www.nikgrozev.org/2014/10/16/rest-with-embedded-jetty-and-jersey-in-a-single-jar-step-by-step/

	public static void main(String[] args) {

        Server jettyServer = new Server(8080);
        
 
     	ServletHolder servletHolder = new ServletHolder("default", new DefaultServlet());
//     	ServletHolder servletHolder = new ServletHolder(ServletContainer.class, "/*");
        
        // Tells the Jersey Servlet which REST service/class to load.
        servletHolder.setInitParameter(
           "jersey.config.server.provider.classnames",
           JSONResource.class.getCanonicalName());

      
      
      
        servletHolder.setInitParameter("resourceBase", "./src/webapp/");
		
		ServletContextHandler context = new ServletContextHandler();
		
        context.addServlet(servletHolder, "/*");
        
        jettyServer.setHandler(context);
        
		try {

			jettyServer.start();
			jettyServer.join();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jettyServer.destroy();
		}

	}

}
