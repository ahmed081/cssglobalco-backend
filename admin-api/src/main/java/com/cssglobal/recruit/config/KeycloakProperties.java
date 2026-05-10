package com.cssglobal.recruit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak")
public record KeycloakProperties(String baseUrl, String realm, String clientId, String clientSecret,
                                 String adminClientId, String adminUsername, String adminPassword) {
}
