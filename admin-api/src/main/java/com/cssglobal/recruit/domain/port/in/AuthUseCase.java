package com.cssglobal.recruit.domain.port.in;

import com.cssglobal.recruit.adapter.in.web.dto.request.LoginRequest;
import com.cssglobal.recruit.adapter.in.web.dto.request.RegisterRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.AuthResponse;
import com.cssglobal.recruit.adapter.in.web.dto.response.UserResponse;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthUseCase {
    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);

    UserResponse me(Jwt jwt);
}
