package com.utp.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
  @Bean
  OpenAPI customOpenAPI() {
    Info info = new Info()
        .title("Api Rest JWT")
        .description("Api Rest con implementación de  JWT")
        .version("1.0");

    String securitySchemeName = "bearerAuth";
    SecurityScheme securityScheme = new SecurityScheme()
        .name(securitySchemeName)
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT");

    return new OpenAPI()
        .info(info)
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(new Components().addSecuritySchemes(securitySchemeName, securityScheme));
  }
}
