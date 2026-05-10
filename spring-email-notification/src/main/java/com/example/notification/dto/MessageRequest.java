package com.example.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageRequest(
        String name,
        String email,
        String interest,
        String company,
        @JsonProperty("appointment_date") String appointmentDate,
        @JsonProperty("appointment_time") String appointmentTime,
        String timezone
) {}
