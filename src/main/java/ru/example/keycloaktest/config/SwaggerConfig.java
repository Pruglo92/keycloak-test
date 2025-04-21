package ru.example.keycloaktest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "keycloak-test", version = "v1"))
@SecurityScheme(
        name = "keycloak",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "${spring.security.oauth2.client.provider.keycloak.issuer-uri}/protocol/openid-connect/auth",
                        tokenUrl = "${spring.security.oauth2.client.provider.keycloak.issuer-uri}/protocol/openid-connect/token",
                        refreshUrl = "${spring.security.oauth2.client.provider.keycloak.issuer-uri}/protocol/openid-connect/token",
                        scopes = {
                                @OAuthScope(name = "openid", description = "Standard OpenID scope"),
                                @OAuthScope(name = "profile", description = "Access to user profile"),
                                @OAuthScope(name = "email", description = "Access to the user's email address")
                        }
                )
        )
)
public class SwaggerConfig {
}