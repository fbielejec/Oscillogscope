package resources;

import java.util.Map;

public class Row {

	private Map<String, Double> row;

	public Row() {
	}

	public Map<String, Double> getRow() {
		return row;
	}

	public void setRow(Map<String, Double> row) {
		this.row = row;
	}

	public String toString() {
		return row.toString();
	}
	
}
