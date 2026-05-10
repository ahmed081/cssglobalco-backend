package com.cssglobal.recruit.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 120)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_on", updatable = false, nullable = false)
    private Instant createdOn;

    @LastModifiedBy
    @Column(name = "updated_by", length = 120)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_on")
    private Instant updatedOn;

    @Version
    private long version;
}
