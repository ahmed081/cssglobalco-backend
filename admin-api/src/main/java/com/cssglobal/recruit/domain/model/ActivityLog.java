package com.cssglobal.recruit.domain.model;

import com.cssglobal.recruit.domain.enums.ActivityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "activity_logs")
public class ActivityLog extends BaseAuditableEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 60)
    private ActivityType type;
    @Column(nullable = false, length = 80)
    private String targetType;
    @Column(nullable = false, length = 80)
    private String targetId;
    @Column(length = 1500)
    private String message;
}
