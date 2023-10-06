package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "email", source = "username")
    Optional<UserEntity> userEntityToUser(UserEntity userEntity);

    @Mapping(target = "username", source = "email")
    UserEntity userToUserEntity(User user);

    UpdateUser userEntityToUpdateUser(UserEntity userEntity);

    UserEntity updateUserToUserEntity(UpdateUser updateUser);

    UserEntity registerToUserEntity(Register register);

}
