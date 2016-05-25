package model;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oModel implements Model {

	private final Sql2o sql2o;

	public Sql2oModel(Sql2o sql2o) {
		this.sql2o = sql2o;
	}

	@Override
	public void createTable(String tableName, List<String> columnNames) {
		/**
		 * Creates table and adds columns to it
		 */
		try (Connection conn = sql2o.beginTransaction()) {

			String sql = "CREATE TABLE IF NOT EXISTS :tableName";
			conn.createQuery(sql).addParameter("tableName", tableName).executeUpdate();

			columnNames.forEach((name) -> {
				conn.createQuery("ALTER TABLE " + tableName + " ADD :name numeric;").addParameter("name", name)
						.executeUpdate();
			});

			conn.commit();
		}
	}

	@Override
	public void dropTable(String tableName) {
		try (Connection conn = sql2o.beginTransaction()) {
			String sql = "DROP TABLE IF EXISTS :tableName";
			conn.createQuery(sql).addParameter("tableName", tableName).executeUpdate();
			conn.commit();
		}

	}

	@Override
	public void insertRow(Map<String, Double> row ) {
	
		try (Connection conn = sql2o.beginTransaction()) {

//			INSERT INTO playground (type, color, location, install_date) VALUES ('slide', 'blue', 'south', '2014-04-28');

			
			String sql = row.entrySet().stream().map(e -> e.toString()).reduce("", String::concat);
			
			
              System.out.println(sql);			
			
			conn.commit();
		}

	}
	
	public String prepareStatement(Map<String, Double> row) {
		String sql = null;
		
		
		
		
		return sql;
	}

	@Override
	public List<Line> getAllRows() {
		 try (Connection conn = sql2o.open()) {
			 
//	            List<Post> posts = conn.createQuery("select * from posts")
//	                    .executeAndFetch(Post.class);
//	            posts.forEach((post) -> post.setCategories(getCategoriesFor(conn, post.getPost_uuid())));
			 
			 
			 
	        }
		
		
		
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
