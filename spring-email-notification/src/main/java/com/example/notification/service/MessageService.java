package com.example.notification.service;

import com.example.notification.dto.MessageRequest;
import com.example.notification.model.Message;
import com.example.notification.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message save(MessageRequest request) {
        Message message = new Message();
        message.setName(request.name());
        message.setEmail(request.email());
        message.setSubject(request.subject());
        message.setBody(request.body());
        return messageRepository.save(message);
    }
}
