package com.cssglobal.recruit.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record InterviewRequest(@NotNull Instant startsAt, @NotNull Instant endsAt, String title, String meetingUrl,
                               String feedback) {
}
