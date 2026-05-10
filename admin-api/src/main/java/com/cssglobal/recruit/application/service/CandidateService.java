package com.cssglobal.recruit.application.service;

import com.cssglobal.recruit.adapter.in.web.dto.request.CandidateRequest;
import com.cssglobal.recruit.adapter.in.web.dto.request.ChangeStageRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.CandidateResponse;
import com.cssglobal.recruit.adapter.out.persistence.CandidateRepository;
import com.cssglobal.recruit.adapter.out.persistence.CandidateStageHistoryRepository;
import com.cssglobal.recruit.adapter.out.persistence.JobRoleRepository;
import com.cssglobal.recruit.application.mapper.CandidateMapper;
import com.cssglobal.recruit.common.ApiException;
import com.cssglobal.recruit.domain.enums.CandidateStage;
import com.cssglobal.recruit.domain.model.Candidate;
import com.cssglobal.recruit.domain.model.CandidateStageHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateService implements com.cssglobal.recruit.domain.port.in.CandidateUseCase {
    private final CandidateRepository repo;
    private final JobRoleRepository jobRepo;
    private final CandidateStageHistoryRepository historyRepo;
    private final CandidateMapper mapper;

    @Transactional(readOnly = true)
    public Page<CandidateResponse> search(String q, CandidateStage stage, String role, Pageable pageable) {
        Specification<Candidate> spec = Specification.where(null);
        if (stage != null) spec = spec.and((r, c, b) -> b.equal(r.get("stage"), stage));
        if (role != null && !role.isBlank())
            spec = spec.and((r, c, b) -> b.equal(b.lower(r.get("role")), role.toLowerCase()));
        if (q != null && !q.isBlank()) {
            String like = "%" + q.toLowerCase() + "%";
            spec = spec.and((r, c, b) -> b.or(b.like(b.lower(r.get("firstName")), like), b.like(b.lower(r.get("lastName")), like), b.like(b.lower(r.get("email")), like), b.like(b.lower(r.get("skills")), like), b.like(b.lower(r.get("role")), like)));
        }
        return repo.findAll(spec, pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public CandidateResponse get(UUID id) {
        return mapper.toResponse(find(id));
    }

    @Transactional
    public CandidateResponse create(CandidateRequest req) {
        if (req.email() != null && repo.existsByEmailIgnoreCase(req.email()))
            throw new ApiException(HttpStatus.CONFLICT, "Candidate email already exists");
        Candidate c = mapper.toEntity(req);
        if (c.getLocation() == null || c.getLocation().isBlank()) c.setLocation("Casablanca, Morocco");
        assignJob(c, req.assignedJobId());
        Candidate saved = repo.save(c);
        addHistory(saved, null, saved.getStage(), "Candidate created");
        return mapper.toResponse(saved);
    }

    @Transactional
    public CandidateResponse update(UUID id, CandidateRequest req) {
        Candidate c = find(id);
        mapper.update(c, req);
        assignJob(c, req.assignedJobId());
        return mapper.toResponse(repo.save(c));
    }

    @Transactional
    public CandidateResponse changeStage(UUID id, ChangeStageRequest req) {
        Candidate c = find(id);
        CandidateStage from = c.getStage();
        c.setStage(req.stage());
        Candidate saved = repo.save(c);
        addHistory(saved, from, req.stage(), req.comment());
        return mapper.toResponse(saved);
    }

    @Transactional
    public void delete(UUID id) {
        repo.delete(find(id));
    }

    private Candidate find(UUID id) {
        return repo.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Candidate not found"));
    }

    private void assignJob(Candidate c, UUID id) {
        c.setAssignedJob(id == null ? null : jobRepo.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Job role not found")));
    }

    private void addHistory(Candidate c, CandidateStage from, CandidateStage to, String comment) {
        CandidateStageHistory h = new CandidateStageHistory();
        h.setCandidate(c);
        h.setFromStage(from);
        h.setToStage(to);
        h.setComment(comment);
        h.setChangedAt(Instant.now());
        historyRepo.save(h);
    }
}
