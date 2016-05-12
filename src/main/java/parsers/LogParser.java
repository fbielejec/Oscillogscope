package parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import data.OscilloscopeData;
import exceptions.OscilloscopeException;
import utils.Utils;

/**
 * @author fbielejec
 */
public class LogParser {

	public static final String HASH_COMMENT = "#";
	private final String filename;

	public LogParser(String filename) {
		this.filename = filename;
	}

	// TODO: with lambdas
	public OscilloscopeData parseLog() throws OscilloscopeException {

		try {


			Pattern pattern = Pattern.compile("\\s+");

			List<String> x = Files.lines(Paths.get(filename), Charset.defaultCharset())
					.map(line -> pattern.split(line, 2)[0])
					.filter(line -> !line.equals(HASH_COMMENT))
					.collect(Collectors.toList());

			
			
			System.out.println(x);
			

			
			
			
			
			
			
			
			
			
			
			
			
		} catch ( Exception e) {
			String message = "Error occured when reading log file " + filename + "\n" + e.getMessage();
			throw new OscilloscopeException(message);
		}

		return null;
	}

	public static String[] readLines(String filename, String comment) throws IOException {

		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {

			// skip commented lines
			if (!line.contains(comment)) {
				lines.add(line);
			} // END: commented line check

		} // END: lines loop

		bufferedReader.close();

		return lines.toArray(new String[lines.size()]);
	}// END: readLines

}
