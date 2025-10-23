package com.conta_bancaria.infrastructure.config;


import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI oficinaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Conta Bancária")
                        .description("Cadastro e gestão de contas para um banco.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Contato")
                                .email("contato@banco.com"))
                );
    }
}
