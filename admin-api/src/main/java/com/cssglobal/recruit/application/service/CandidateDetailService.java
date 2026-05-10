package com.cssglobal.recruit.application.service;

import com.cssglobal.recruit.adapter.in.web.dto.request.CandidateNoteRequest;
import com.cssglobal.recruit.adapter.in.web.dto.request.InterviewRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.CandidateNoteResponse;
import com.cssglobal.recruit.adapter.in.web.dto.response.CandidateStageHistoryResponse;
import com.cssglobal.recruit.adapter.in.web.dto.response.InterviewResponse;
import com.cssglobal.recruit.adapter.out.persistence.CandidateNoteRepository;
import com.cssglobal.recruit.adapter.out.persistence.CandidateRepository;
import com.cssglobal.recruit.adapter.out.persistence.CandidateStageHistoryRepository;
import com.cssglobal.recruit.adapter.out.persistence.InterviewRepository;
import com.cssglobal.recruit.common.ApiException;
import com.cssglobal.recruit.domain.enums.NoteVisibility;
import com.cssglobal.recruit.domain.model.Candidate;
import com.cssglobal.recruit.domain.model.CandidateNote;
import com.cssglobal.recruit.domain.model.CandidateStageHistory;
import com.cssglobal.recruit.domain.model.Interview;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateDetailService {
    private final CandidateRepository candidateRepo;
    private final CandidateNoteRepository noteRepo;
    private final CandidateStageHistoryRepository historyRepo;
    private final InterviewRepository interviewRepo;

    @Transactional(readOnly = true)
    public List<CandidateStageHistoryResponse> history(UUID candidateId) {
        Candidate c = find(candidateId);
        return historyRepo.findAll((r, q, b) -> b.equal(r.get("candidate").get("id"), c.getId())).stream().map(this::history).toList();
    }

    @Transactional(readOnly = true)
    public List<CandidateNoteResponse> notes(UUID candidateId) {
        Candidate c = find(candidateId);
        return noteRepo.findAll((r, q, b) -> b.equal(r.get("candidate").get("id"), c.getId())).stream().map(this::note).toList();
    }

    @Transactional
    public CandidateNoteResponse addNote(UUID candidateId, CandidateNoteRequest request) {
        CandidateNote n = new CandidateNote();
        n.setCandidate(find(candidateId));
        n.setContent(request.content());
        n.setVisibility(request.visibility() == null ? NoteVisibility.INTERNAL : request.visibility());
        return note(noteRepo.save(n));
    }

    @Transactional
    public void deleteNote(UUID noteId) {
        noteRepo.deleteById(noteId);
    }

    @Transactional(readOnly = true)
    public List<InterviewResponse> interviews(UUID candidateId) {
        Candidate c = find(candidateId);
        return interviewRepo.findAll((r, q, b) -> b.equal(r.get("candidate").get("id"), c.getId())).stream().map(this::interview).toList();
    }

    @Transactional
    public InterviewResponse addInterview(UUID candidateId, InterviewRequest request) {
        Interview i = new Interview();
        i.setCandidate(find(candidateId));
        i.setStartsAt(request.startsAt());
        i.setEndsAt(request.endsAt());
        i.setTitle(request.title());
        i.setMeetingUrl(request.meetingUrl());
        i.setFeedback(request.feedback());
        return interview(interviewRepo.save(i));
    }

    @Transactional
    public void deleteInterview(UUID interviewId) {
        interviewRepo.deleteById(interviewId);
    }

    @Transactional(readOnly = true)
    public String shareText(UUID candidateId) {
        Candidate c = find(candidateId);
        return "CSS GLOBAL — CANDIDATE PROFILE\n\n" +
                "Name: " + c.getFirstName() + " " + c.getLastName() + "\n" +
                "Role: " + c.getRole() + " (" + c.getSeniority() + ")\n" +
                "Location: " + safe(c.getLocation()) + "\n" +
                "Languages: " + c.getLanguages() + "\n" +
                "Rating: " + c.getRating() + "/5\n" +
                "Skills: " + safe(c.getSkills()) + "\n" +
                "Email: " + safe(c.getEmail()) + "\n" +
                "Phone: " + safe(c.getPhone()) + "\n" +
                "LinkedIn: " + safe(c.getLinkedinUrl()) + "\n" +
                "CV: " + safe(c.getCvUrl()) + "\n\nPresented by CSS Global";
    }

    private Candidate find(UUID id) {
        return candidateRepo.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Candidate not found"));
    }

    private String safe(String s) {
        return s == null || s.isBlank() ? "N/A" : s;
    }

    private CandidateNoteResponse note(CandidateNote n) {
        return new CandidateNoteResponse(n.getId(), n.getCandidate().getId(), n.getVisibility(), n.getContent(), n.getCreatedOn(), n.getUpdatedOn());
    }

    private CandidateStageHistoryResponse history(CandidateStageHistory h) {
        return new CandidateStageHistoryResponse(h.getId(), h.getCandidate().getId(), h.getFromStage(), h.getToStage(), h.getComment(), h.getChangedAt(), h.getCreatedOn());
    }

    private InterviewResponse interview(Interview i) {
        return new InterviewResponse(i.getId(), i.getCandidate().getId(), i.getStartsAt(), i.getEndsAt(), i.getTitle(), i.getMeetingUrl(), i.getFeedback(), i.getCreatedOn(), i.getUpdatedOn());
    }
}
