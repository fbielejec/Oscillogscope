package model;

import java.util.List;

public class ColumnNames {

	private List<String> colnames;

	public ColumnNames() {
	}
	
	public List<String> getColumnNames() {
		return colnames;
	}

	public void setColumnNames(List<String> colnames) {
		this.colnames = colnames;
	}
	
	@Override
	public String toString() {
		return colnames.toString();
	}
	
}
