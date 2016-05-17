package resources;

// TODO: test:
// http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/

public class Resource {

	private String resource = "";

	private Resource() {
		// lazy singleton
	}

	private static class Holder {
		private static final Resource INSTANCE = new Resource();
	}

	public static Resource getInstance() {
		return Holder.INSTANCE;
	}

	public String getResource() {
		return resource;
	}

	public synchronized void setResource(String resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return resource;
	}

}
