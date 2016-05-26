package resources;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.List;

import org.sql2o.Sql2o;

import com.google.gson.Gson;

import data.Line;
import data.OscilloscopeData;
import model.Database;
import model.Sql2oDatabase;
import utils.JsonTransformer;

public class ModelResource {

	private static final String API_CONTEXT = "/database";
	private static final String TEST_TABLE = "test";

	private final Database db;

	public ModelResource() {

		String username = "postgres";
		String password = "chaos555";
		String db_name = "postgres";
		String url = "jdbc:postgresql://localhost:5432/" + db_name;
		Sql2o sql2o = new Sql2o(url, username, password);
		this.db = new Sql2oDatabase(sql2o);

		setupEndpoints();
	}

	private void setupEndpoints() {

		// post for column names, calls model createTable
		post(API_CONTEXT + "/colnames", (request, response) -> {
			try {

				ColumnNames columns = new Gson().fromJson(request.body(), ColumnNames.class);

				db.createTable(TEST_TABLE, columns.getColumnNames());

				response.status(200);
				response.type("application/json");
				return response;

			} catch (Exception e) {
				e.printStackTrace();
				response.status(400);
				return response;
			}
		});

		// post for row inserts
		post(API_CONTEXT + "/insert", (request, response) -> {
			try {

				Row row = new Gson().fromJson(request.body(), Row.class);

				db.insertRow(row.getRow(), TEST_TABLE);

				response.status(200);
				response.type("application/json");
				return response;

			} catch (Exception e) {
				e.printStackTrace();
				response.status(400);
				return response;
			}
		});

		get(API_CONTEXT + "/data", "application/json", (request, response) -> {

			List<String> colnames = db.getColumnNames(TEST_TABLE);

			List<Line> lines = db.getAllRows(colnames, TEST_TABLE);
			
			
			
			
			return new OscilloscopeData(lines);
			
		} , new JsonTransformer());

	}

}
