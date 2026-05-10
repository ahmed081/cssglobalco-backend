package com.cssglobal.recruit.adapter.in.web.dto.request;

import com.cssglobal.recruit.domain.enums.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record JobRoleRequest(
        @NotBlank String title,
        @NotBlank String department,
        UUID clientCompanyId,
        String location,
        SeniorityLevel seniority,
        JobStatus status,
        JobPriority priority,
        ContractType contractType,
        WorkMode workMode,
        Integer openings,
        LocalDate deadline,
        BigDecimal salaryMin,
        BigDecimal salaryMax,
        String description,
        String requiredSkills,
        String niceToHaveSkills,
        Set<LanguageCode> languages
) {
}
