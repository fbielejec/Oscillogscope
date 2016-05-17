package resources;

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

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return resource;
	}

}
