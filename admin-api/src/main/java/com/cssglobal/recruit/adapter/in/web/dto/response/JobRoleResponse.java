package com.cssglobal.recruit.adapter.in.web.dto.response;

import com.cssglobal.recruit.domain.enums.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record JobRoleResponse(
        UUID id, String title, String department, UUID clientCompanyId, String clientCompanyName,
        String location, SeniorityLevel seniority, JobStatus status, JobPriority priority,
        ContractType contractType, WorkMode workMode, Integer openings, LocalDate deadline,
        BigDecimal salaryMin, BigDecimal salaryMax, String description, String requiredSkills,
        String niceToHaveSkills, Set<LanguageCode> languages, long candidateCount,
        Instant createdOn, Instant updatedOn
) {
}
