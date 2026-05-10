package com.cssglobal.recruit.adapter.in.web.dto.response;

public record AuthResponse(String accessToken, String refreshToken, Long expiresIn, String tokenType,
                           UserResponse user) {
}
