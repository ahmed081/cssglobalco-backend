package com.cssglobal.recruit.adapter.in.web.dto.response;

import com.cssglobal.recruit.domain.enums.AppRole;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(UUID id, String keycloakUserId, String email, String firstName, String lastName,
                           String phone, AppRole role, String language, boolean active, Instant createdOn,
                           Instant updatedOn) {
}
