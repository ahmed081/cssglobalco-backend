package com.cssglobal.recruit.adapter.in.web.dto.request;

import com.cssglobal.recruit.domain.enums.CandidateStage;
import com.cssglobal.recruit.domain.enums.LanguageCode;
import com.cssglobal.recruit.domain.enums.SeniorityLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record CandidateRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email,
        String phone,
        String linkedinUrl,
        String location,
        String source,
        @NotBlank String role,
        SeniorityLevel seniority,
        CandidateStage stage,
        Integer rating,
        Integer yearsOfExperience,
        BigDecimal salaryExpectation,
        LocalDate availabilityDate,
        String currentCompany,
        String skills,
        String cvUrl,
        String notes,
        String tags,
        String avatarUrl,
        UUID assignedJobId,
        Set<LanguageCode> languages
) {
}
