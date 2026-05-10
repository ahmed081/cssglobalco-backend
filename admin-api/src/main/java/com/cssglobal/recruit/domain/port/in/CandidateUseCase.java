package com.cssglobal.recruit.domain.port.in;

import com.cssglobal.recruit.adapter.in.web.dto.request.CandidateRequest;
import com.cssglobal.recruit.adapter.in.web.dto.request.ChangeStageRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.CandidateResponse;
import com.cssglobal.recruit.domain.enums.CandidateStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CandidateUseCase {
    Page<CandidateResponse> search(String q, CandidateStage stage, String role, Pageable pageable);

    CandidateResponse get(UUID id);

    CandidateResponse create(CandidateRequest request);

    CandidateResponse update(UUID id, CandidateRequest request);

    CandidateResponse changeStage(UUID id, ChangeStageRequest request);

    void delete(UUID id);
}
