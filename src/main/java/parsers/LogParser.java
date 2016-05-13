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
import java.util.Collection;
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

	public OscilloscopeData parseLog() throws OscilloscopeException {

		try {

			// skip commented lines
			List<String> linesList = Files.lines(Paths.get(filename), Charset.defaultCharset()) //
					.filter(line -> !line.startsWith(HASH_COMMENT)) //
					.collect(Collectors.toList());

			// get states
            List<String> states = parseColumn(linesList, 0).stream().skip(1).collect(Collectors.toList());
			
//			Utils.headCollection(states,10);
			
			
            
            
			
			
			
			

		} catch (Exception e) {
			String message = "Error occured when reading log file " + filename + "\n" + e.getMessage();
			throw new OscilloscopeException(message);
		}

		return null;
	}

	private List<String> parseColumn(Collection<String> linesList, int colIndex) {

		Pattern pattern = Pattern.compile("\\s+");

		List<String> coll = linesList.stream() //
				.map(line -> {
					return pattern.split(line, -1)[colIndex];
				}) //
				.filter(line -> !line.equals(HASH_COMMENT)) //
				.collect(Collectors.toList());

		return coll;
	}

}
