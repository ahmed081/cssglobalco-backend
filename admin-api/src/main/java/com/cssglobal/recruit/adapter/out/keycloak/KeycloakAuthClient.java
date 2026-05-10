package com.cssglobal.recruit.adapter.out.keycloak;

import com.cssglobal.recruit.adapter.in.web.dto.request.LoginRequest;
import com.cssglobal.recruit.config.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KeycloakAuthClient {
    private final WebClient.Builder webClientBuilder;
    private final KeycloakProperties props;

    public TokenResult login(LoginRequest request) {
        var form = new LinkedMultiValueMap<String, String>();
        form.add("grant_type", "password");
        form.add("client_id", props.clientId());
        form.add("client_secret", props.clientSecret());
        form.add("username", request.email());
        form.add("password", request.password());
        Map<?, ?> body = webClientBuilder.build().post()
                .uri(props.baseUrl() + "/realms/" + props.realm() + "/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(form))
                .retrieve().bodyToMono(Map.class).block();
        return new TokenResult((String) body.get("access_token"), (String) body.get("refresh_token"), ((Number) body.get("expires_in")).longValue(), (String) body.get("token_type"));
    }

    public record TokenResult(String accessToken, String refreshToken, Long expiresIn, String tokenType) {
    }
}
