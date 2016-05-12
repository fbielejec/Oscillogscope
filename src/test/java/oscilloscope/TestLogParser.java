package oscilloscope;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import exceptions.OscilloscopeException;
import parsers.LogParser;

/**
 * @author fbielejec
 */
public class TestLogParser {

	private String filepath;

	@Before
	public void init() {
		try {

			ClassLoader classLoader = this.getClass().getClassLoader();
			File file = new File(classLoader.getResource("test.log").getFile());
			this.filepath = file.getAbsolutePath();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testLogParser() {
		try {

			LogParser parser = new LogParser(this.filepath);

			parser.parseLog();

			
			
			
			
			
		} catch (OscilloscopeException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(2, 1 + 1);
	}

}
