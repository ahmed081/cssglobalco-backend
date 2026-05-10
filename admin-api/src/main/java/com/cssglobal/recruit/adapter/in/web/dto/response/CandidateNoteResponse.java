package com.cssglobal.recruit.adapter.in.web.dto.response;

import com.cssglobal.recruit.domain.enums.NoteVisibility;

import java.time.Instant;
import java.util.UUID;

public record CandidateNoteResponse(UUID id, UUID candidateId, NoteVisibility visibility, String content,
                                    Instant createdOn, Instant updatedOn) {
}
