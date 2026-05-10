package com.cssglobal.recruit.domain.model;

import com.cssglobal.recruit.domain.enums.CandidateStage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "candidate_stage_history")
public class CandidateStageHistory extends BaseAuditableEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private CandidateStage fromStage;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private CandidateStage toStage;
    @Column(length = 1000)
    private String comment;
    @Column(nullable = false)
    private Instant changedAt = Instant.now();
}
