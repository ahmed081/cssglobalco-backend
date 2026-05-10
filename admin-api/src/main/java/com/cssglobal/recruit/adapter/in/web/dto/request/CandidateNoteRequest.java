package com.cssglobal.recruit.adapter.in.web.dto.request;

import com.cssglobal.recruit.domain.enums.NoteVisibility;
import jakarta.validation.constraints.NotBlank;

public record CandidateNoteRequest(NoteVisibility visibility, @NotBlank String content) {
}
