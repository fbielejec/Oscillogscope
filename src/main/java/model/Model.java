package model;

import java.util.List;
import java.util.Map;

public interface Model {

	public void createTable(String tableName, List<String> columnNames);
	
	public void dropTable(String tableName);
	
	void insertRow(Map<String, Double> row, String tableName);
	
	public List<Line> getAllRows(List<String> columnNames, String tableName);
	
	public boolean tableExists(String tableName);


}

