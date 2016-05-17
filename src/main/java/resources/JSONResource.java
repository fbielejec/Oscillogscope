package resources;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/test")
public class JSONResource {

	Resource resource =  Resource.getInstance();
	Gson gson = new GsonBuilder().create();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getStringResource() {
		
//		System.out.println("GET: " + this.resource.getResource());
		
		return this.gson.toJson(this.resource);
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putStringResource(Resource json) {
		
		this.resource.setResource(json.getResource());
		
//		System.out.println("POST: " + json.getResource());
		
		return Response.status(Status.OK).entity(json).build();
	}
	
}
