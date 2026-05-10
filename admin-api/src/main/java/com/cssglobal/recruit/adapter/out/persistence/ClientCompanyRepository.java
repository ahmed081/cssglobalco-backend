package com.cssglobal.recruit.adapter.out.persistence;

import com.cssglobal.recruit.domain.model.ClientCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ClientCompanyRepository extends JpaRepository<ClientCompany, UUID>, JpaSpecificationExecutor<ClientCompany> {
}
