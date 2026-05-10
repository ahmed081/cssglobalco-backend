package com.cssglobal.recruit.adapter.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientCompanyRequest(
        @NotBlank String name,
        String contactName,
        @Email String contactEmail,
        String phone,
        String logoUrl,
        String city,
        String country,
        Boolean active
) {
}
