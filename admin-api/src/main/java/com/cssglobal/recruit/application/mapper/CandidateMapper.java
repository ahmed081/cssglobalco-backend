package com.cssglobal.recruit.application.mapper;

import com.cssglobal.recruit.adapter.in.web.dto.request.CandidateRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.CandidateResponse;
import com.cssglobal.recruit.domain.model.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = CommonMapperConfig.class)
public interface CandidateMapper {
    @Mapping(target = "fullName", expression = "java(entity.getFirstName() + \" \" + entity.getLastName())")
    @Mapping(target = "assignedJobId", source = "assignedJob.id")
    @Mapping(target = "assignedJobTitle", source = "assignedJob.title")
    CandidateResponse toResponse(Candidate entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignedJob", ignore = true)
    Candidate toEntity(CandidateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignedJob", ignore = true)
    void update(@MappingTarget Candidate entity, CandidateRequest request);
}
