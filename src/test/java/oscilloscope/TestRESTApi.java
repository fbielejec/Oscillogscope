package oscilloscope;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;

/**
 * @author fbielejec
 */
public class TestRESTApi {

	@BeforeClass
	public static void beforeClass() {
		Oscilloscope.main(null);
	}

	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

	@Test
	public void testTestResource() {
		try {

			TestResponse res = TestUtils.request("POST", "/test?resource=FUBAR");
			Map<String, String> json = res.json();

			assertEquals(200, res.status);
			assertEquals("FUBAR", json.get("resource"));

		} catch (IOException e) {
//			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
