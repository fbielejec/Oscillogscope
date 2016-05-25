package model;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oModel implements Model {

	private final Sql2o sql2o;

	public Sql2oModel(Sql2o sql2o) {
		this.sql2o = sql2o;
	}

	@Override
	public void createTable(String tableName, List<String> columnNames) {

		String sql = null;
		
		try (Connection conn = sql2o.beginTransaction()) {
				
			  sql = "CREATE TABLE IF NOT EXISTS :tableName";
				conn.createQuery(sql).addParameter("tableName", tableName).executeUpdate();
				
				columnNames.forEach(
						
						(name) -> {
							
                conn.createQuery("ALTER TABLE " + tableName + " ADD :name real;")
                .addParameter("name", name)
                .executeUpdate();
                
//						System.out.println(name);
						}
						);
				
        conn.commit();
				
				
		}
	}

	
	
	@Override
	public void dropTable(String tableName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertRow(List<Double> values) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Double> getRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tableExists(String tableName) {
		try (Connection conn = sql2o.beginTransaction()) {

			DatabaseMetaData md = conn.getJdbcConnection().getMetaData();

			ResultSet rs = md.getTables(null, null, tableName, null);
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
