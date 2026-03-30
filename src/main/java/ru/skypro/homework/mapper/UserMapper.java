package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "image", target = "image")
    @Mapping(target = "role", expression = "java(mapRole(userEntity.getRole()))")
    User toDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", constant = "USER")
    UserEntity toEntity(Register register);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateUserFromDto(UpdateUser updateUser, @MappingTarget UserEntity userEntity);

    default Role mapRole(String role) {
        if (role == null) {
            return Role.USER;
        }
        try {
            return Role.valueOf(role);
        } catch (IllegalArgumentException e) {
            return Role.USER;
        }
    }

    default void updatePassword(UserEntity userEntity, String newPassword) {
        userEntity.setPassword(newPassword);
    }
}