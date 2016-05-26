package resources;

import static spark.Spark.post;

import org.sql2o.Sql2o;

import com.google.gson.Gson;

import model.ColumnNames;
import model.Model;
import model.Sql2oModel;

public class ModelResource {

	private static final String API_CONTEXT = "/colnames";
	private static final String TEST_DT = "test";
	
	
	private final Model model; 

	public ModelResource() {

		String username = "postgres";
		String password = "chaos555";
		String db_name = "postgres";
		String url = "jdbc:postgresql://localhost:5432/" + db_name;
		Sql2o sql2o = new Sql2o(url, username, password);
		this.model = new Sql2oModel(sql2o);

		setupEndpoints();
	}

	private void setupEndpoints() {

		// post for column names, calld model,createtable
		post(API_CONTEXT , (request, response) -> {
			try {

				
				System.out.println(request.body());
				
				ColumnNames colnames = new Gson().fromJson(request.body(), ColumnNames.class);
				
				System.out.println(colnames.toString());
				
                model.createTable(TEST_DT, colnames.getColumnNames());
				
				
				
				response.status(200);
				response.type("application/json");
				return response;

			} catch (Exception e) {
				
				e.printStackTrace();
				
				response.status(400);
				return response;
			}
		});
		
		
//		get(API_CONTEXT, "application/json", (request, response) -> this.test, new JsonTransformer());
		
		
	}
	
	
}
