package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
			
			String[] lines = readLines(this.filename, HASH_COMMENT);
			
			Utils.headArray(lines, 10);
			
			
			
			
			
			
			
		} catch (IOException e) {
			
			String message = "Error occured when reading log file " + filename + "\n"+ e.getMessage();
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
