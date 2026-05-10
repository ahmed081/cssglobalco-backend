package com.cssglobal.recruit.adapter.in.web.dto.response;

import com.cssglobal.recruit.domain.enums.CandidateStage;

import java.time.Instant;
import java.util.UUID;

public record CandidateStageHistoryResponse(UUID id, UUID candidateId, CandidateStage fromStage, CandidateStage toStage,
                                            String comment, Instant changedAt, Instant createdOn) {
}
