package resources;

public class Test {

	private String resource = "";

	public Test() {
	}
	
//	private Test() {
//		// lazy singleton
//	}
//
//	private static class Holder {
//		private static final Test INSTANCE = new Test();
//	}
//
//	public static Test getInstance() {
//		return Holder.INSTANCE;
//	}

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
