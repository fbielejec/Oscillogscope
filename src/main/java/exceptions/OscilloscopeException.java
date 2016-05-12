package exceptions;

public class OscilloscopeException extends Exception {

	private static final long serialVersionUID = 2624453296766775277L;
	private final String message;
	
	public OscilloscopeException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
