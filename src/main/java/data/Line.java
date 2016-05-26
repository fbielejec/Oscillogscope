package data;

import java.util.List;

/**
 * @author fbielejec
 */
public class Line {

	private final String name;
	private final List<Coordinate> values;
	
	public Line(String name, List<Coordinate> values) {
		this.name = name;
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public List<Coordinate> getValues() {
		return values;
	}
	
}
