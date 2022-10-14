package Ejercicios_4_5_6.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("API REST para laptops hecha con Spring Boot",
                "Laptops API REST",
                "v1.0",
                "https://policies.google.com/terms?hl=es-PE&fg=1",
                new Contact("Daniel","https://www.google.com/","daniel@ejemplo.com"),
                "MIT",
                "https://www.google.com/",
                Collections.emptyList());
    }
}
