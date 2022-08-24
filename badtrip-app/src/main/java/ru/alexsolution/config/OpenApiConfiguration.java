package ru.alexsolution.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openApi(@Value("${spring.application.name}") String applicationName,
                           @Value("${openapi.security.scheme.name}") String schemeName,
                           @Value("${openapi.version}") String apiVersion) {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(new Components()
                        .addSecuritySchemes(schemeName, new SecurityScheme()
                                .name(schemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                .info(new Info().title(applicationName.toUpperCase(Locale.ROOT)).version(apiVersion)
                        .description("You need set Authorization token from FRKK request POST https://frkk-dso.apps.ds1-genr01.corp.dev.vtb/login " +
                                "with body: username=director_ca&password=password"));
    }
}
