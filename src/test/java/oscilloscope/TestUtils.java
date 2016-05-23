package oscilloscope;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import spark.utils.IOUtils;

public class TestUtils {

	public static TestResponse request(String method, String path) throws IOException {
		URL url = new URL("http://localhost:8080" + path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		connection.setDoOutput(true);
		connection.connect();
		String body = IOUtils.toString(connection.getInputStream());
		return new TestResponse(connection.getResponseCode(), body);
	}

}
