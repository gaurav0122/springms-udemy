package com.task;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
					title ="Open API documentation For Task Service",
					description = "Documentation for task service ",
					version = "1.0",
					contact = @Contact(
								name = "gaurav",
								email = "gaurv@gmail.com",
								url = "url.url"
							),
					license = @License(
								name = "Licence name",
								url = "Licenceurl"
							)
				),
		externalDocs = @ExternalDocumentation(
					url = "External-documentation",
					description = "this is external documentation"
				)
)
public class TaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}
	
	@Bean
	public WebClient getWEbClient() {
		return WebClient.builder().build();
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
