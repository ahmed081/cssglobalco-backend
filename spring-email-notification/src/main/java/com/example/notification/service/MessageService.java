package com.example.notification.service;

import com.example.notification.dto.MessageRequest;
import com.example.notification.model.Message;
import com.example.notification.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final EmailNotificationService emailNotificationService;

    @Transactional
    public Message save(MessageRequest request) {
        Message message = new Message();
        message.setName(request.name());
        message.setEmail(request.email());
        message.setCompany(request.company());
        message.setInterest(request.interest());
        message.setAppointmentDate(request.appointmentDate());
        message.setAppointmentTime(request.appointmentTime());
        message.setTimezone(request.timezone());
        Message saved = messageRepository.save(message);

        emailNotificationService.sendAppointmentConfirmation(
                request.email(),
                request.name(),
                request.appointmentDate(),
                request.appointmentTime(),
                request.timezone(),
                request.interest(),
                request.company()
        );

        return saved;
    }
}
