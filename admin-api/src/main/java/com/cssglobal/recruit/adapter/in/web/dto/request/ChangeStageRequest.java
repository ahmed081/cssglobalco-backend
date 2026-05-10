package com.cssglobal.recruit.adapter.in.web.dto.request;

import com.cssglobal.recruit.domain.enums.CandidateStage;
import jakarta.validation.constraints.NotNull;

public record ChangeStageRequest(@NotNull CandidateStage stage, String comment) {
}
