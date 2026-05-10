package com.example.notification.service;

import com.example.notification.dto.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    public void sendSimpleEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject(request.subject());
        message.setText(request.body());
        mailSender.send(message);
    }

    @Async
    public void sendAppointmentConfirmation(String to, String name, String date, String time, String timezone, String interest, String company) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Appointment Confirmation – CSS Global Co.");
        message.setText(
                "Dear " + name + ",\n\n" +
                "Thank you for reaching out to CSS Global Co.\n\n" +
                "Your appointment has been successfully scheduled. Here are the details:\n\n" +
                "  Date:     " + date + "\n" +
                "  Time:     " + time + " (" + timezone + ")\n" +
                "  Interest: " + interest + "\n" +
                "  Company:  " + company + "\n\n" +
                "Our team will be in touch with you shortly to confirm any further details.\n\n" +
                "Best regards,\n" +
                "CSS Global Co. Team"
        );
        mailSender.send(message);
    }
}
