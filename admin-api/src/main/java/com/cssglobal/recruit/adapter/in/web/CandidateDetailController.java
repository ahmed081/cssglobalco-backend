package com.cssglobal.recruit.adapter.in.web;

import com.cssglobal.recruit.adapter.in.web.dto.request.CandidateNoteRequest;
import com.cssglobal.recruit.adapter.in.web.dto.request.InterviewRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.CandidateNoteResponse;
import com.cssglobal.recruit.adapter.in.web.dto.response.CandidateStageHistoryResponse;
import com.cssglobal.recruit.adapter.in.web.dto.response.InterviewResponse;
import com.cssglobal.recruit.application.service.CandidateDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/candidates/{candidateId}")
@RequiredArgsConstructor
public class CandidateDetailController {
    private final CandidateDetailService service;

    @GetMapping("/history")
    public List<CandidateStageHistoryResponse> history(@PathVariable UUID candidateId) {
        return service.history(candidateId);
    }

    @GetMapping("/notes")
    public List<CandidateNoteResponse> notes(@PathVariable UUID candidateId) {
        return service.notes(candidateId);
    }

    @PostMapping("/notes")
    public CandidateNoteResponse addNote(@PathVariable UUID candidateId, @Valid @RequestBody CandidateNoteRequest request) {
        return service.addNote(candidateId, request);
    }

    @DeleteMapping("/notes/{noteId}")
    public void deleteNote(@PathVariable UUID candidateId, @PathVariable UUID noteId) {
        service.deleteNote(noteId);
    }

    @GetMapping("/interviews")
    public List<InterviewResponse> interviews(@PathVariable UUID candidateId) {
        return service.interviews(candidateId);
    }

    @PostMapping("/interviews")
    public InterviewResponse addInterview(@PathVariable UUID candidateId, @Valid @RequestBody InterviewRequest request) {
        return service.addInterview(candidateId, request);
    }

    @DeleteMapping("/interviews/{interviewId}")
    public void deleteInterview(@PathVariable UUID candidateId, @PathVariable UUID interviewId) {
        service.deleteInterview(interviewId);
    }

    @GetMapping("/share-text")
    public Map<String, String> shareText(@PathVariable UUID candidateId) {
        return Map.of("text", service.shareText(candidateId));
    }
}
