package oscilloscope;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import resources.JSONResource;

/**
 * @fbielejec
 */

public class Oscilloscope {

	public static void main(String[] args) {

		Server jettyServer = new Server(8080);

		////////////////////////
		// ---JSON RESOURCE---//
		////////////////////////

		JSONResource resource = new JSONResource();
		
		ResourceConfig rc = new ResourceConfig();
		rc.register(resource);

		ServletContainer sc = new ServletContainer(rc);

		ServletHolder servletHolder = new ServletHolder(sc);

		ServletContextHandler jsonResourceContext = new ServletContextHandler();
		jsonResourceContext.addServlet(servletHolder, "/*");

		//////////////////////////
		// ---STATIC RESOURCE---//
		//////////////////////////

		ResourceHandler staticResourceHandler = new ResourceHandler();

		staticResourceHandler.setResourceBase("./src/webapp/");

		//  enable Directory Listing
//		resourceHandler.setDirectoriesListed(true);

		ContextHandler staticContextHandler = new ContextHandler("/");
		staticContextHandler.setHandler(staticResourceHandler);

		///////////////////////
		// ---ADD HANDLERS---//
		///////////////////////
		
		HandlerList handlers = new HandlerList();

		handlers.setHandlers(new Handler[] { jsonResourceContext, //
				staticContextHandler, //
				new DefaultHandler() //
		});

		jettyServer.setHandler(handlers);

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
