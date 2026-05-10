package com.cssglobal.recruit.application.service;

import com.cssglobal.recruit.adapter.in.web.dto.request.JobRoleRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.JobRoleResponse;
import com.cssglobal.recruit.adapter.out.persistence.CandidateRepository;
import com.cssglobal.recruit.adapter.out.persistence.ClientCompanyRepository;
import com.cssglobal.recruit.adapter.out.persistence.JobRoleRepository;
import com.cssglobal.recruit.application.mapper.JobRoleMapper;
import com.cssglobal.recruit.common.ApiException;
import com.cssglobal.recruit.domain.enums.JobStatus;
import com.cssglobal.recruit.domain.model.JobRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobRoleService implements com.cssglobal.recruit.domain.port.in.JobRoleUseCase {
    private final JobRoleRepository repo;
    private final ClientCompanyRepository clientRepo;
    private final CandidateRepository candidateRepo;
    private final JobRoleMapper mapper;

    @Transactional(readOnly = true)
    public Page<JobRoleResponse> search(String q, JobStatus status, Pageable pageable) {
        Specification<JobRole> spec = Specification.where(null);
        if (status != null) spec = spec.and((r, c, b) -> b.equal(r.get("status"), status));
        if (q != null && !q.isBlank()) {
            String like = "%" + q.toLowerCase() + "%";
            spec = spec.and((r, c, b) -> b.or(b.like(b.lower(r.get("title")), like), b.like(b.lower(r.get("department")), like), b.like(b.lower(r.get("location")), like)));
        }
        return repo.findAll(spec, pageable).map(this::withCount);
    }

    @Transactional(readOnly = true)
    public JobRoleResponse get(UUID id) {
        return withCount(find(id));
    }

    @Transactional
    public JobRoleResponse create(JobRoleRequest req) {
        JobRole j = mapper.toEntity(req);
        assignClient(j, req.clientCompanyId());
        return withCount(repo.save(j));
    }

    @Transactional
    public JobRoleResponse update(UUID id, JobRoleRequest req) {
        JobRole j = find(id);
        mapper.update(j, req);
        assignClient(j, req.clientCompanyId());
        return withCount(repo.save(j));
    }

    @Transactional
    public void delete(UUID id) {
        repo.delete(find(id));
    }

    private JobRole find(UUID id) {
        return repo.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Job role not found"));
    }

    private void assignClient(JobRole j, UUID id) {
        j.setClientCompany(id == null ? null : clientRepo.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Client not found")));
    }

    private JobRoleResponse withCount(JobRole j) {
        JobRoleResponse r = mapper.toResponse(j);
        long count = candidateRepo.count((root, query, cb) -> cb.equal(root.get("assignedJob").get("id"), j.getId()));
        return new JobRoleResponse(r.id(), r.title(), r.department(), r.clientCompanyId(), r.clientCompanyName(), r.location(), r.seniority(), r.status(), r.priority(), r.contractType(), r.workMode(), r.openings(), r.deadline(), r.salaryMin(), r.salaryMax(), r.description(), r.requiredSkills(), r.niceToHaveSkills(), r.languages(), count, r.createdOn(), r.updatedOn());
    }
}
