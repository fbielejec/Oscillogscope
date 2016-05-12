package data;

import java.util.ArrayList;
import java.util.List;

public class OscilloscopeData {

	private final List<Line> data;

	public OscilloscopeData() {
		this.data = new ArrayList<Line>();
	}

	public List<Line> getData() {
		return data;
	}

}
