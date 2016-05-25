package model;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		/**
		 * Drops table entirely
		 */
		try (Connection conn = sql2o.beginTransaction()) {
			String sql = "DROP TABLE IF EXISTS :tableName";
			conn.createQuery(sql).addParameter("tableName", tableName).executeUpdate();
			conn.commit();
		}

	}

	@Override
	public void insertRow(Map<String, Double> row, String tableName) {
		/**
		 * Inserts one row of data
		 * 
		 * @param row:
		 *            map which defines column - value pairs
		 */

		try (Connection conn = sql2o.beginTransaction()) {
			String sql = prepareStatement(row, tableName);
			conn.createQuery(sql).executeUpdate();
			conn.commit();
		}
	}

	public String prepareStatement(Map<String, Double> row, String tableName) {
		/**
		 * Prepares a statement e.g. INSERT INTO test (name3, name2, name1)
		 * VALUES (2.0, 1.0, 0.0);
		 */

		Collection<String> cols = row.keySet();
		String colString = stringifyCollection(cols);

		Collection<Double> vals = row.values();
		String valString = stringifyCollection(vals);

		String sql = "INSERT INTO test " + colString + " VALUES " + valString + ";";

		return sql;
	}

	private String stringifyCollection(Collection<?> coll) {
		String str = "(";
		str = str.concat(coll.stream().map(e -> e.toString()).reduce("", (prev, next) -> prev.concat(next + ",")));
		str = str.substring(0, str.length() - 1).concat(")");
		return str;
	}

	@Override
	public List<Line> getAllRows(List<String> columnNames, String tableName) {
		
		List<Line> lines = null;
		try (Connection conn = sql2o.open()) {

			Integer nColumns = columnNames.size();
			Integer colIndex = 0;
			String statesColumnName = columnNames.get(colIndex);

			// get states column
			List<Double> states = getColumn(statesColumnName, tableName);

			// get lines
			lines = IntStream.range(colIndex++, nColumns - 1) //
					.mapToObj(i -> {
						String colname = columnNames.get(i);
						List<Double> values = getColumn(colname, tableName);
						Line line = createLine(colname, states, values);
						return line;
					}) //
					.collect(Collectors.toList());

		}

		return lines;
	}

	private Line createLine(String colname, List<Double> states, List<Double> values) {
		/**
		 * maps a lambda which creates Coordinates from list of states and list
		 * of db column values
		 * 
		 * @return Line
		 */
		List<Coordinate> coords = IntStream.range(0, values.size() - 1) //
				.skip(1) //
				.mapToObj(i -> {
					Double x = Double.valueOf(states.get(i));
					Double y = Double.valueOf(values.get(i));
					return new Coordinate(x, y);
				}) //
				.collect(Collectors.toList());

		return new Line(colname, coords);
	}

	private List<Double> getColumn(String colname, String tableName) {
		try (Connection conn = sql2o.open()) {
			String sql = "SELECT " + colname + " FROM " + tableName;
			return conn.createQuery(sql).executeScalarList(Double.class);
		}
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
