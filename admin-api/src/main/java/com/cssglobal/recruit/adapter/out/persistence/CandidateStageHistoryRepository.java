package com.cssglobal.recruit.adapter.out.persistence;

import com.cssglobal.recruit.domain.model.CandidateStageHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CandidateStageHistoryRepository extends JpaRepository<CandidateStageHistory, UUID>, JpaSpecificationExecutor<CandidateStageHistory> {
}
