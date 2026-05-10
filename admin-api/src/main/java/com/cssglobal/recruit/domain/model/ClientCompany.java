package com.cssglobal.recruit.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client_companies")
public class ClientCompany extends BaseAuditableEntity {
    @Column(nullable = false, length = 160)
    private String name;
    @Column(length = 160)
    private String contactName;
    @Column(length = 160)
    private String contactEmail;
    @Column(length = 40)
    private String phone;
    @Column(length = 500)
    private String logoUrl;
    @Column(length = 120)
    private String city;
    @Column(length = 120)
    private String country;
    @Column(nullable = false)
    private boolean active = true;
}
