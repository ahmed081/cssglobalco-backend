package com.cssglobal.recruit.adapter.in.web;

import com.cssglobal.recruit.adapter.in.web.dto.request.JobRoleRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.JobRoleResponse;
import com.cssglobal.recruit.application.service.JobRoleService;
import com.cssglobal.recruit.domain.enums.JobStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class JobRoleController {
    private final JobRoleService service;

    @GetMapping
    public Page<JobRoleResponse> search(@RequestParam(required = false) String q, @RequestParam(required = false) JobStatus status, Pageable pageable) {
        return service.search(q, status, pageable);
    }

    @GetMapping("/{id}")
    public JobRoleResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PostMapping
    public JobRoleResponse create(@Valid @RequestBody JobRoleRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public JobRoleResponse update(@PathVariable UUID id, @Valid @RequestBody JobRoleRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
