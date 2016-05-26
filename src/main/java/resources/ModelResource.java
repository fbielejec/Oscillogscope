package resources;

import static spark.Spark.post;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.sql2o.Sql2o;

import com.google.gson.Gson;

import model.Model;
import model.Sql2oModel;

public class ModelResource {

	private static final String API_CONTEXT = "/database";
	private static final String TEST_TABLE = "test";
	
	
	private final Model model; 
//    private List<String> colnames;
	
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

		// post for column names, calls model createTable
		post(API_CONTEXT +"/colnames", (request, response) -> {
			try {
				
				ColumnNames columns = new Gson().fromJson(request.body(), ColumnNames.class);
//				this.colnames = columns.getColumnNames();
				
                model.createTable(TEST_TABLE, columns.getColumnNames());
				
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
		post(API_CONTEXT +"/insert", (request, response) -> {
			try {

				Row row = new Gson().fromJson(request.body(), Row.class);
				
				model.insertRow(row.getRow(), TEST_TABLE);
				
				response.status(200);
				response.type("application/json");
				return response;

			} catch (Exception e) {
				e.printStackTrace();
				response.status(400);
				return response;
			}
		});
		
		
	}
	
//	private Map<String, Double> createRowMap(List<String> colnames, List<Double> values) {
//		
//		  Map<String, Double> commercials =
//				  colnames
//		            .stream()
//		            .collect(
//		                    Collectors.toMap(k->k, 
//		                    		v->null
//		                    		));
//		  
//		  System.out.println(commercials);
//		  
//		return null;
//	}
//	
}
