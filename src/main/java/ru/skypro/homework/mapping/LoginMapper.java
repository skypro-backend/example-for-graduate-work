package ru.skypro.homework.mapping;

import liquibase.pro.packaged.L;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.LoginDto;

@Mapper
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);
    LoginDto loginToDto(LoginDto loginDto);
}
