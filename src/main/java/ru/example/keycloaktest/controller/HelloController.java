package ru.example.keycloaktest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Tag(
        name = "Greeting API",
        description = "API для демонстрации работы Keycloak авторизации"
)
public class HelloController {

    @Operation(
            summary = "Получить приветствие",
            description = """
                    ### Требования:
                    - Требуется аутентификация через Keycloak
                                
                    ### Ответ:
                    - Возвращает приветствие с параметрами юзера с сервера авторизации
                    """,
            security = @SecurityRequirement(name = "keycloak")
    )
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthToken) {
            Jwt jwt = jwtAuthToken.getToken();

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("user_id", jwt.getSubject());
            response.put("username", jwt.getClaimAsString("preferred_username"));
            response.put("email", jwt.getClaimAsString("email"));
            response.put("full_name", jwt.getClaimAsString("name"));
            response.put("scopes", jwtAuthToken.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList());

            StringBuilder formatted = new StringBuilder("Hello user with parameters:\n");
            response.forEach((key, value) -> formatted.append(key).append(": ").append(value).append("\n"));

            return ResponseEntity.ok(formatted.toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error, user not authenticated with JWT");
    }
}