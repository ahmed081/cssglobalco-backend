package com.cssglobal.recruit.adapter.out.persistence;

import com.cssglobal.recruit.domain.enums.CandidateStage;
import com.cssglobal.recruit.domain.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID>, JpaSpecificationExecutor<Candidate> {
    Page<Candidate> findByStage(CandidateStage stage, Pageable pageable);

    boolean existsByEmailIgnoreCase(String email);
}
