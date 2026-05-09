package com.example.notification.controller;

import com.example.notification.dto.EmailRequest;
import com.example.notification.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications/email")
@RequiredArgsConstructor
public class EmailNotificationController {

    private final EmailNotificationService emailNotificationService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody EmailRequest request) {
        emailNotificationService.sendSimpleEmail(request);
        return ResponseEntity.accepted().build();
    }
}
