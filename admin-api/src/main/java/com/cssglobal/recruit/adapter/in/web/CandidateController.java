package com.cssglobal.recruit.adapter.in.web;

import com.cssglobal.recruit.adapter.in.web.dto.request.CandidateRequest;
import com.cssglobal.recruit.adapter.in.web.dto.request.ChangeStageRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.CandidateResponse;
import com.cssglobal.recruit.application.service.CandidateService;
import com.cssglobal.recruit.domain.enums.CandidateStage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService service;

    @GetMapping
    public Page<CandidateResponse> search(@RequestParam(required = false) String q, @RequestParam(required = false) CandidateStage stage, @RequestParam(required = false) String role, Pageable pageable) {
        return service.search(q, stage, role, pageable);
    }

    @GetMapping("/{id}")
    public CandidateResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PostMapping
    public CandidateResponse create(@Valid @RequestBody CandidateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public CandidateResponse update(@PathVariable UUID id, @Valid @RequestBody CandidateRequest request) {
        return service.update(id, request);
    }

    @PatchMapping("/{id}/stage")
    public CandidateResponse changeStage(@PathVariable UUID id, @Valid @RequestBody ChangeStageRequest request) {
        return service.changeStage(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
