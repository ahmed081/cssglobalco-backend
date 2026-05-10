package com.cssglobal.recruit.adapter.in.web.dto.response;

import java.time.Instant;
import java.util.UUID;

public record InterviewResponse(UUID id, UUID candidateId, Instant startsAt, Instant endsAt, String title,
                                String meetingUrl, String feedback, Instant createdOn, Instant updatedOn) {
}
