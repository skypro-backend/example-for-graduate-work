package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.UserEntity;

/**
 * UserMapper
 * <br><i>содержит методы:</i>
 *
 * <br>"_____ toEntity___"</i>
 * <br>- toEntity <i>(из {@link User} в  {@link UserEntity})</i>;
 * <br>- toEntity <i>(из {@link Login} в  {@link UserEntity})</i>;
 * <br>- toEntity <i>(из {@link Register} в  {@link UserEntity})</i>;
 * <br>- toEntity <i>(из {@link UpdateUser} в  {@link UserEntity})</i>;
 * <br>- toEntity <i>(из {@link NewPassword} в  {@link UserEntity})</i>;
 *
 * <br>"_____ toDto___"</i>
 * <br>- toDtoUser <i>(из {@link UserEntity} в  {@link User})</i>;
 * <br>- toDtoLogin <i>(из {@link UserEntity} в  {@link Login})</i>;
 * <br>- toDtoRegister <i>(из {@link UserEntity} в  {@link Register})</i>;
 * <br>- toDtoUpdateUser <i>(из {@link UserEntity} в  {@link UpdateUser})</i>;
 * <br>- toDtoNewPassword <i>(из {@link UserEntity} в  {@link NewPassword})</i>;
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)//
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // to use UserMapper.INSTANCE.toEntity();
    // to use UserMapper.INSTANCE.toDto___();

    //_____ toEntity___

    @Mapping(source = "username", target = "email")
    UserEntity toEntity(Login dto);

    @Mapping(source = "currentPassword", target = "email")
    UserEntity toEntity(NewPassword dto);

    @Mapping(source = "username", target = "email")
    UserEntity toEntity(Register dto);

    UserEntity toEntity(UpdateUser dto);

    @Mapping(source = "image", target = "image.filePath")
    UserEntity toEntity(User dto);


    //    //_____ toDto___
    @Mapping(source = "email", target = "username")
    Login toDtoLogin(UserEntity userEntity);

    @Mapping(source = "password", target = "newPassword")
    NewPassword toDtoNewPassword(UserEntity userEntity);

    @Mapping(source = "email", target = "username")
    Register toDtoRegister(UserEntity userEntity);

    UpdateUser toDtoUpdateUser(UserEntity userEntity);

    @Mapping(source = "image.filePath", target = "image")
    User toDtoUser(UserEntity userEntity);
}
