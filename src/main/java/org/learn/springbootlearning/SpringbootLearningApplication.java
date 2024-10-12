package org.learn.springbootlearning;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringbootLearningApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringbootLearningApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLearningApplication.class, args);
		logger.info("Project started with gradle build tool..........");
		// Create a Gson instance
		final Gson gson = new Gson();

		// Create a JsonObject and add dummy data
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", 1);
		jsonObject.addProperty("name", "John Doe");
		jsonObject.addProperty("email", "john.doe@example.com");

		// Convert JsonObject to JSON string
		String jsonString = gson.toJson(jsonObject);

		// Print the JSON string
		logger.info("JSON data - {}", jsonString);
	}

}
