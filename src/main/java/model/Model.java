package model;

import java.util.List;

public interface Model {

	public void createTable(String tableName, List<String> columnNames);
	
	public void dropTable(String tableName);
	
	public void insertRow(List<Double> values);
	
}

