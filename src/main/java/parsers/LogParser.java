package parsers;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import data.OscilloscopeData;
import exceptions.OscilloscopeException;
import model.Coordinate;
import model.Line;
import utils.Utils;

/**
 * @author fbielejec
 */
public class LogParser {

	public static final String HASH_COMMENT = "#";
	public static final String WHITESPACE = "\\s+";
	private final String filename;

	public LogParser(String filename) {
		this.filename = filename;
	}

	public OscilloscopeData parseLog() throws OscilloscopeException {

		final List<Line> lines;

		try {

			// skip commented lines
			List<String> linesList = Files.lines(Paths.get(filename), Charset.defaultCharset()) //
					.filter(line -> !line.startsWith(HASH_COMMENT)) //
					.collect(Collectors.toList());

			// get states
			Integer colIndex = 0;
			List<String> states = parseColumn(linesList, colIndex).stream().skip(1).collect(Collectors.toList());

			Integer nColumns = getNColumns(linesList);

			// get lines
			lines = IntStream.range(colIndex++, nColumns - 1) //
					.parallel() //
					.mapToObj(i -> {
						return createLine(linesList, states, i);
					}) //
					.collect(Collectors.toList());

		} catch (Exception e) {
			String message = "Error occured when reading log file " + filename + "\n" + e.getMessage();
			throw new OscilloscopeException(message);
		}

		return new OscilloscopeData(lines);
	}

	private Integer getNColumns(List<String> linesList) {
		return linesList.get(0).split(WHITESPACE).length;
	}

	private List<String> parseColumn(Collection<String> linesList, Integer colIndex) {

		Pattern pattern = Pattern.compile(WHITESPACE);

		List<String> rows = linesList.stream() //
				.map(line -> {
					return pattern.split(line, -1)[colIndex];
				}) //
				.filter(line -> !line.equals(HASH_COMMENT)) //
				.collect(Collectors.toList());

		return rows;
	}

	private Line createLine(Collection<String> linesList, List<String> states, Integer colIndex) {

		List<String> column = parseColumn(linesList, colIndex);
		String name = column.get(0);

		List<Coordinate> values = IntStream.range(0, column.size() - 1) //
				.skip(1) //
				.mapToObj(i -> {
					Double x = Double.valueOf(states.get(i));
					Double y = Double.valueOf(column.get(i));
					return new Coordinate(x, y);
				}) //
				.collect(Collectors.toList());

		return new Line(name, values);
	}

}
