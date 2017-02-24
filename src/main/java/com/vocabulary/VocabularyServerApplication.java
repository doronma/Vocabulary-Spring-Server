package com.vocabulary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.vocabulary"})
@EnableMongoRepositories({"com.vocabulary.repository"})
@EnableSwagger2
public class VocabularyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocabularyServerApplication.class, args);
	}
	
	 @Bean
	    public Docket newsApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("Vocabulary")
	                .apiInfo(apiInfo())
	                .select()
	                .paths(PathSelectors.any())
	                .build();
	    }
	     
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Vocabulary Application")
	                .description("Vocabulary Application - Spring REST with Swagger")
	                .version("1.0")
	                .build();
	    }

}
