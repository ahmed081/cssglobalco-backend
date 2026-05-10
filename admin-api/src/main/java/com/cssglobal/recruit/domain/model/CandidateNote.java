package com.cssglobal.recruit.domain.model;

import com.cssglobal.recruit.domain.enums.NoteVisibility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "candidate_notes")
public class CandidateNote extends BaseAuditableEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private NoteVisibility visibility = NoteVisibility.INTERNAL;
    @Column(nullable = false, length = 4000)
    private String content;
}
