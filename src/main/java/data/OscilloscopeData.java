package data;

import java.util.List;

import model.Line;

/**
 * @author fbielejec
 */
public class OscilloscopeData {

	private final List<Line> data;

	public OscilloscopeData(List<Line> data) {
		this.data = data;
	}

	public List<Line> getLines() {
		return data;
	}

}
