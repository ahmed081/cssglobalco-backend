package com.cssglobal.recruit.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Set<String> roles = new HashSet<>();
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.get("roles") instanceof Collection<?> r)
            r.forEach(x -> roles.add(String.valueOf(x)));
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) resourceAccess.values().forEach(v -> {
            if (v instanceof Map<?, ?> m && m.get("roles") instanceof Collection<?> r)
                r.forEach(x -> roles.add(String.valueOf(x)));
        });
        return roles.stream().map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r.toUpperCase()).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
