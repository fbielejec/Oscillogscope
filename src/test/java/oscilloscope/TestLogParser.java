package oscilloscope;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import data.OscilloscopeData;
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

			// value which I Just Know (TM)
			final int NUMBER_OF_COLUMNS = 212;
			final int NUMBER_OF_VALUES = 998;

			LogParser parser = new LogParser(this.filepath);
			OscilloscopeData data = parser.parseLog();

			int nlines = data.getLines().size();
			assertEquals(NUMBER_OF_COLUMNS, nlines);

			int nCoords = data.getLines().get(0).getValues().size();
			assertEquals(NUMBER_OF_VALUES, nCoords);

		} catch (OscilloscopeException e) {
			e.printStackTrace();
		}
	}

}
