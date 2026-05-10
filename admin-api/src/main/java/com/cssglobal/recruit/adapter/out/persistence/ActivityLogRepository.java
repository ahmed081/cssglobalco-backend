package com.cssglobal.recruit.adapter.out.persistence;

import com.cssglobal.recruit.domain.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID>, JpaSpecificationExecutor<ActivityLog> {
}
