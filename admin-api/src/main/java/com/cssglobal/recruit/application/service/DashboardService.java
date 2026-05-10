package com.cssglobal.recruit.application.service;

import com.cssglobal.recruit.adapter.in.web.dto.response.DashboardResponse;
import com.cssglobal.recruit.adapter.out.persistence.CandidateRepository;
import com.cssglobal.recruit.adapter.out.persistence.JobRoleRepository;
import com.cssglobal.recruit.application.mapper.CandidateMapper;
import com.cssglobal.recruit.domain.enums.CandidateStage;
import com.cssglobal.recruit.domain.enums.JobStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService implements com.cssglobal.recruit.domain.port.in.DashboardUseCase {
    private final CandidateRepository candidateRepo;
    private final JobRoleRepository jobRepo;
    private final CandidateMapper candidateMapper;

    @Transactional(readOnly = true)
    public DashboardResponse get() {
        long total = candidateRepo.count();
        long placed = candidateRepo.count((r, q, b) -> b.equal(r.get("stage"), CandidateStage.PLACED));
        long openRoles = jobRepo.count((r, q, b) -> b.equal(r.get("status"), JobStatus.OPEN));
        List<DashboardResponse.StageCount> stageCounts = Arrays.stream(CandidateStage.values()).map(s -> new DashboardResponse.StageCount(s, candidateRepo.count((r, q, b) -> b.equal(r.get("stage"), s)))).toList();
        List<DashboardResponse.RoleCount> roleCounts = candidateRepo.findAll().stream().collect(Collectors.groupingBy(c -> c.getRole() == null ? "Unknown" : c.getRole(), Collectors.counting())).entrySet().stream().map(e -> new DashboardResponse.RoleCount(e.getKey(), e.getValue())).toList();
        var recent = candidateRepo.findAll(PageRequest.of(0, 5, org.springframework.data.domain.Sort.by("createdOn").descending())).map(candidateMapper::toResponse).getContent();
        return new DashboardResponse(total, placed, total - placed, openRoles, stageCounts, roleCounts, recent);
    }
}
