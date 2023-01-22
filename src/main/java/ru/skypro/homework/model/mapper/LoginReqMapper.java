package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.dto.LoginReqDto;
import ru.skypro.homework.model.entity.LoginReq;

@Mapper
public interface LoginReqMapper {
LoginReqMapper INSTANCE = Mappers.getMapper(LoginReqMapper.class);
@Mapping(source = "username", target = "username", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Mapping(source = "password", target = "password", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LoginReqDto toDto(LoginReq loginReq);
}
