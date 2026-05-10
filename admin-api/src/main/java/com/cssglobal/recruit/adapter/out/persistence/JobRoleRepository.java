package com.cssglobal.recruit.adapter.out.persistence;

import com.cssglobal.recruit.domain.model.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JobRoleRepository extends JpaRepository<JobRole, UUID>, JpaSpecificationExecutor<JobRole> {
}
