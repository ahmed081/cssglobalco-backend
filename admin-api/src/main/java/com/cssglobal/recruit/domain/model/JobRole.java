package com.cssglobal.recruit.domain.model;

import com.cssglobal.recruit.domain.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "job_roles")
public class JobRole extends BaseAuditableEntity {
    @Column(nullable = false, length = 180)
    private String title;
    @Column(nullable = false, length = 80)
    private String department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_company_id")
    private ClientCompany clientCompany;
    @Column(length = 180)
    private String location;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private SeniorityLevel seniority = SeniorityLevel.MID;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private JobStatus status = JobStatus.OPEN;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private JobPriority priority = JobPriority.MEDIUM;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ContractType contractType = ContractType.FULL_TIME;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private WorkMode workMode = WorkMode.REMOTE;
    private Integer openings = 1;
    private LocalDate deadline;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    @Column(length = 4000)
    private String description;
    @Column(length = 1500)
    private String requiredSkills;
    @Column(length = 1500)
    private String niceToHaveSkills;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "job_role_languages", joinColumns = @JoinColumn(name = "job_role_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "language_code", length = 8)
    private Set<LanguageCode> languages = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AppUser owner;
}
