package com.bank.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "Building loan microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "M Swati",
						email = "m.swati4198@gmail.com",
						url = "https://www.linkedin.com/in/mswati07/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.linkedin.com/in/mswati07/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Loans microservice REST API Documentation",
				url = "https://www.bank.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
