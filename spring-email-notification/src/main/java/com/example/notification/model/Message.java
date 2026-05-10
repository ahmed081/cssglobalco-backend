package com.example.notification.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String interest;

    @Column(name = "appointment_date")
    private String appointmentDate;

    @Column(name = "appointment_time")
    private String appointmentTime;

    @Column
    private String timezone;

    @Column(nullable = false)
    private LocalDateTime sentAt = LocalDateTime.now();
}
