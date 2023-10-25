package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Mapper
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder encoder;

    @Mapping(target = "login", source = "username")
    @Mapping(target = "password", expression = "java(encryptPassword(registerDto))")
    public abstract User toEntity(RegisterDto registerDto);

    @Mapping(target = "email", source = "login")
    @Mapping(target = "image", source = "userImage.filePath")
    public abstract UserDto toDto(User user);

    protected String encryptPassword(RegisterDto registerDto) {
        return encoder.encode(registerDto.password());
    }
}