package com.example.notification.controller;

import com.example.notification.dto.MessageRequest;
import com.example.notification.model.Message;
import com.example.notification.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) {
        Message saved = messageService.save(request);
        return ResponseEntity.ok(saved);
    }
}
