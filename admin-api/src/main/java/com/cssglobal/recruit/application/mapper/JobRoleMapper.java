package com.cssglobal.recruit.application.mapper;

import com.cssglobal.recruit.adapter.in.web.dto.request.JobRoleRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.JobRoleResponse;
import com.cssglobal.recruit.domain.model.JobRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = CommonMapperConfig.class)
public interface JobRoleMapper {
    @Mapping(target = "clientCompanyId", source = "clientCompany.id")
    @Mapping(target = "clientCompanyName", source = "clientCompany.name")
    @Mapping(target = "candidateCount", ignore = true)
    JobRoleResponse toResponse(JobRole entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clientCompany", ignore = true)
    JobRole toEntity(JobRoleRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clientCompany", ignore = true)
    void update(@MappingTarget JobRole entity, JobRoleRequest request);
}
