package com.cssglobal.recruit.application.service;

import com.cssglobal.recruit.adapter.in.web.dto.request.LoginRequest;
import com.cssglobal.recruit.adapter.in.web.dto.request.RegisterRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.AuthResponse;
import com.cssglobal.recruit.adapter.in.web.dto.response.UserResponse;
import com.cssglobal.recruit.adapter.out.keycloak.KeycloakAdminAdapter;
import com.cssglobal.recruit.adapter.out.keycloak.KeycloakAuthClient;
import com.cssglobal.recruit.adapter.out.persistence.AppUserRepository;
import com.cssglobal.recruit.application.mapper.UserMapper;
import com.cssglobal.recruit.common.ApiException;
import com.cssglobal.recruit.domain.enums.AppRole;
import com.cssglobal.recruit.domain.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements com.cssglobal.recruit.domain.port.in.AuthUseCase {
    private final KeycloakAdminAdapter keycloakAdmin;
    private final KeycloakAuthClient keycloakAuth;
    private final AppUserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmailIgnoreCase(request.email()).isPresent())
            throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
        String keycloakId = keycloakAdmin.createUser(request.email(), request.firstName(), request.lastName(), request.password());
        AppUser u = new AppUser();
        u.setKeycloakUserId(keycloakId);
        u.setEmail(request.email());
        u.setFirstName(request.firstName());
        u.setLastName(request.lastName());
        u.setPhone(request.phone());
        u.setRole(request.role() == null ? AppRole.RECRUITER : request.role());
        userRepository.save(u);
        return login(new LoginRequest(request.email(), request.password()));
    }

    @Transactional(readOnly = true)
    public UserResponse me(Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        if (email == null) email = jwt.getSubject();
        AppUser user = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Local user profile not found"));
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        var token = keycloakAuth.login(request);
        AppUser user = userRepository.findByEmailIgnoreCase(request.email()).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Local user profile not found"));
        return new AuthResponse(token.accessToken(), token.refreshToken(), token.expiresIn(), token.tokenType(), userMapper.toResponse(user));
    }
}
