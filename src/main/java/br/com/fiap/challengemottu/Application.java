package br.com.fiap.challengemottu;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "API MOTTU", description = "Challenge API REST", version = "v1"))
// @EnableCaching // Habilita o suporte a cache do Spring
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
