package com.cssglobal.recruit.adapter.in.web.dto.response;

import com.cssglobal.recruit.domain.enums.CandidateStage;

import java.util.List;

public record DashboardResponse(long totalCandidates, long placed, long inPipeline, long openRoles,
                                List<StageCount> stageCounts, List<RoleCount> roleCounts,
                                List<CandidateResponse> recentCandidates) {
    public record StageCount(CandidateStage stage, long count) {
    }

    public record RoleCount(String role, long count) {
    }
}
