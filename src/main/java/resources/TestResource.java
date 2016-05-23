package resources;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import utils.JsonTransformer;

public class TestResource {

	private static final String API_CONTEXT = "/test";
	private final Test test;

	public TestResource(Test test) {
		this.test = test;
		setupEndpoints();
	}

	private void setupEndpoints() {

		get(API_CONTEXT, "application/json", (request, response) -> this.test, new JsonTransformer());

		post(API_CONTEXT, (request, response) -> {
			try {

				Test parsedTest = new Gson().fromJson(request.body(), Test.class);

				this.test.setResource(parsedTest.getResource());

				response.status(200);
				response.type("application/json");
				return response;

			} catch (Exception e) {
				response.status(400);
				return response;
			}
		});

	}

}
