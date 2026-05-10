package com.cssglobal.recruit.domain.model;

import com.cssglobal.recruit.domain.enums.AppRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app_users", indexes = {@Index(name = "idx_app_user_email", columnList = "email", unique = true), @Index(name = "idx_app_user_keycloak", columnList = "keycloak_user_id", unique = true)})
public class AppUser extends BaseAuditableEntity {
    @Column(name = "keycloak_user_id", nullable = false, unique = true, length = 80)
    private String keycloakUserId;
    @Column(nullable = false, unique = true, length = 160)
    private String email;
    @Column(name = "first_name", length = 80)
    private String firstName;
    @Column(name = "last_name", length = 80)
    private String lastName;
    @Column(length = 30)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AppRole role = AppRole.RECRUITER;
    @Column(nullable = false, length = 8)
    private String language = "en";
    @Column(nullable = false)
    private boolean active = true;
}
