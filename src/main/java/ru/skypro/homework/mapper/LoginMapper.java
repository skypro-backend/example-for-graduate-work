package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoginMapper {
//    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);
//    @Mapping(target = "user.password", ignore = true)
//    @Mapping(source = "user.userName", target = "userName")
//    LoginDto toDto(User user);
    @Mapping(source = "loginDto.username", target = "userName")
    @Mapping(source = "loginDto.password", target = "password")
    User toModel(LoginDto loginDto);
}
