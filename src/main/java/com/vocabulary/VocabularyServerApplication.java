package com.vocabulary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"controller"})
@EnableMongoRepositories({"repository"})
public class VocabularyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocabularyServerApplication.class, args);
	}
}
