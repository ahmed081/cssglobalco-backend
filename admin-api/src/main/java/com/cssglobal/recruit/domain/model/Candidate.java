package com.cssglobal.recruit.domain.model;

import com.cssglobal.recruit.domain.enums.CandidateStage;
import com.cssglobal.recruit.domain.enums.CandidateStatus;
import com.cssglobal.recruit.domain.enums.LanguageCode;
import com.cssglobal.recruit.domain.enums.SeniorityLevel;
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
@Table(name = "candidates", indexes = {@Index(name = "idx_candidate_email", columnList = "email"), @Index(name = "idx_candidate_stage", columnList = "stage")})
public class Candidate extends BaseAuditableEntity {
    @Column(nullable = false, length = 80)
    private String firstName;
    @Column(nullable = false, length = 80)
    private String lastName;
    @Column(length = 160)
    private String email;
    @Column(length = 40)
    private String phone;
    @Column(length = 500)
    private String linkedinUrl;
    @Column(length = 180)
    private String location;
    @Column(length = 100)
    private String source;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private CandidateStage stage = CandidateStage.NEW;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private CandidateStatus status = CandidateStatus.ACTIVE;
    @Column(nullable = false, length = 80)
    private String role;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private SeniorityLevel seniority = SeniorityLevel.MID;
    private Integer rating = 3;
    private Integer yearsOfExperience;
    private BigDecimal salaryExpectation;
    private LocalDate availabilityDate;
    @Column(length = 180)
    private String currentCompany;
    @Column(length = 2000)
    private String skills;
    @Column(length = 500)
    private String cvUrl;
    @Column(length = 4000)
    private String notes;
    @Column(length = 120)
    private String tags;
    @Column(length = 500)
    private String avatarUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_role_id")
    private JobRole assignedJob;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AppUser owner;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "candidate_languages", joinColumns = @JoinColumn(name = "candidate_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "language_code", length = 8)
    private Set<LanguageCode> languages = new HashSet<>();
}
