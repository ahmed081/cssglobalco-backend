package com.cssglobal.recruit.domain.port.in;

import com.cssglobal.recruit.adapter.in.web.dto.request.JobRoleRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.JobRoleResponse;
import com.cssglobal.recruit.domain.enums.JobStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface JobRoleUseCase {
    Page<JobRoleResponse> search(String q, JobStatus status, Pageable pageable);

    JobRoleResponse get(UUID id);

    JobRoleResponse create(JobRoleRequest request);

    JobRoleResponse update(UUID id, JobRoleRequest request);

    void delete(UUID id);
}
