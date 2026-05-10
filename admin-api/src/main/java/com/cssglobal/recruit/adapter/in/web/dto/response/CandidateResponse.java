package com.cssglobal.recruit.adapter.in.web.dto.response;

import com.cssglobal.recruit.domain.enums.CandidateStage;
import com.cssglobal.recruit.domain.enums.CandidateStatus;
import com.cssglobal.recruit.domain.enums.LanguageCode;
import com.cssglobal.recruit.domain.enums.SeniorityLevel;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record CandidateResponse(
        UUID id, String firstName, String lastName, String fullName, String email, String phone,
        String linkedinUrl, String location, String source, CandidateStage stage, CandidateStatus status,
        String role, SeniorityLevel seniority, Integer rating, Integer yearsOfExperience,
        BigDecimal salaryExpectation, LocalDate availabilityDate, String currentCompany, String skills,
        String cvUrl, String notes, String tags, String avatarUrl, UUID assignedJobId, String assignedJobTitle,
        Set<LanguageCode> languages, Instant createdOn, Instant updatedOn
) {
}
