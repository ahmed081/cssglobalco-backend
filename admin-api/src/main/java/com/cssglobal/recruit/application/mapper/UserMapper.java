package com.cssglobal.recruit.application.mapper;

import com.cssglobal.recruit.adapter.in.web.dto.response.UserResponse;
import com.cssglobal.recruit.domain.model.AppUser;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfig.class)
public interface UserMapper {
    UserResponse toResponse(AppUser user);
}
