package com.cssglobal.recruit.adapter.in.web.dto.request;

import com.cssglobal.recruit.domain.enums.AppRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email @NotBlank String email,
        @NotBlank @Size(min = 8) String password,
        String phone,
        AppRole role
) {
}
