package ru.skypro.homework.mapping;

import liquibase.pro.packaged.L;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.model.Login;

@Mapper
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);
    LoginDto loginToDto(Login login);
}
