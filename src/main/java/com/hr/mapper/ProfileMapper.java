package com.hr.mapper;

import com.hr.dto.RegisterDto;
import com.hr.model.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile fromRegisterDto(RegisterDto dto);
}
