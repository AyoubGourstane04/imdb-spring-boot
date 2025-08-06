package com.imdb.imdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class ImdbApplication {

	//come back to this
	// @Bean 
	// public ObjectMapper objectMapper() {

	// 	var objectMapper = new ObjectMapper();
		

	// 	return objectMapper;
	// }
	public static void main(String[] args) {
		SpringApplication.run(ImdbApplication.class, args);
	}


}
