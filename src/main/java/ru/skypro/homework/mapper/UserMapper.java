package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role",
            expression = "java(register.getRole() != null ? register.getRole() : ru.skypro.homework.dto.Role.USER)")
    @Mapping(target = "image", ignore = true)
    UserEntity toEntity(Register register);
}