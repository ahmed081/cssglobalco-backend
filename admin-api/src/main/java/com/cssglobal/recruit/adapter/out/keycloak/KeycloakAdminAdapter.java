package com.cssglobal.recruit.adapter.out.keycloak;

import com.cssglobal.recruit.config.KeycloakProperties;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KeycloakAdminAdapter {
    private final KeycloakProperties props;

    public String createUser(String email, String firstName, String lastName, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(email);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);
        user.setEmailVerified(false);
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        user.setCredentials(List.of(credential));
        try (Keycloak kc = admin()) {
            RealmResource realm = kc.realm(props.realm());
            Response response = realm.users().create(user);
            if (response.getStatus() >= 300)
                throw new IllegalStateException("Keycloak user creation failed: HTTP " + response.getStatus());
            String location = response.getLocation().toString();
            return location.substring(location.lastIndexOf('/') + 1);
        }
    }

    private Keycloak admin() {
        return KeycloakBuilder.builder()
                .serverUrl(props.baseUrl())
                .realm("master")
                .clientId(props.adminClientId())
                .username(props.adminUsername())
                .password(props.adminPassword())
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }
}
