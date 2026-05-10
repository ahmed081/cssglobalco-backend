package com.cssglobal.recruit.adapter.out.persistence;

import com.cssglobal.recruit.domain.model.CandidateNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CandidateNoteRepository extends JpaRepository<CandidateNote, UUID>, JpaSpecificationExecutor<CandidateNote> {
}
