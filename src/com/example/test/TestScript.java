package com.example.test;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.controller.RestExecutor;

public class TestScript {

	private static final String URL = "http://jsonplaceholder.typicode.com";

	private static RestExecutor executor;


	@BeforeClass
	public static void setUp() {
		/*
		 * Initialize RestExecutor object using the end point URL
		 */
		executor = new RestExecutor(URL);
	}

	@Test
	public void testGETMethod() {
		/*
		 * Performs GET operation on http://jsonplaceholder.typicode.com/posts.
		 * Note that we give only the path in the get method as we use
		 * the domain part while initializing the RestExecutor object
		 */
		  executor.get("/posts")
				.expectCode(200)			// Expected code of 200
				.expectMessage("OK")		// Expected Message of 'OK'
				.expectHeader("Content-Type", "application/json; charset=utf-8") // Content-Type header value
				.expectInBody("\"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\"");	// Content inside the response body



		/*
		 * GET for a specific item
		 */
		executor.get("/posts/11")
				.expectCode(200)
				.expectMessage("OK")
				.expectHeader("Content-Type", "application/json; charset=utf-8")
				.expectInBody("\"title\": \"et ea vero quia laudantium autem\"");

	}


	@Test
	public void testPOSTMethod() {
		/*
		 * POST operation for insertion providing the path, xml content and content type.
		 */
		executor.post("/posts", "{ \"title\": \"I love REST testing\", \"author\": \"REST Tester\" }", "application/json")
				.expectCode(201)
				.expectMessage("Created")
				.expectHeader("Content-Type", "application/json; charset=utf-8")
				.expectInBody("\"title\": \"I love REST testing\"")
				.expectInBody("\"author\": \"REST Tester\"");
	}


}
