package vanq.demo.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Users Microservice API")
                                .version("v1")
                                .description("Microservicio para la administración de usuarios.")
                                .contact(
                                        new Contact()
                                                .name("Vanq Development Team")
                                                .email("dev@vanq.com")
                                )
                                .license(
                                        new License()
                                                .name("Apache 2.0")
                                )
                );
    }

}