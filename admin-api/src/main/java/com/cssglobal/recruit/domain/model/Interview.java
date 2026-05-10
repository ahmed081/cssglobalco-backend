package com.cssglobal.recruit.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "interviews")
public class Interview extends BaseAuditableEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    @Column(nullable = false)
    private Instant startsAt;
    @Column(nullable = false)
    private Instant endsAt;
    @Column(length = 180)
    private String title;
    @Column(length = 500)
    private String meetingUrl;
    @Column(length = 1200)
    private String feedback;
}
