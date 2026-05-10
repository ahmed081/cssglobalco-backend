package com.cssglobal.recruit.adapter.out.persistence;

import com.cssglobal.recruit.domain.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface InterviewRepository extends JpaRepository<Interview, UUID>, JpaSpecificationExecutor<Interview> {
}
