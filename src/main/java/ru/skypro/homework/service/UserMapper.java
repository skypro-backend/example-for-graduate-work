package ru.skypro.homework.service;

import ru.skypro.homework.dto.authentication.Register;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.entity.UserEntity;

public interface UserMapper {

    UserEntity toUserEntity(Register register);

    User toUser(UserEntity userEntity);

    UserEntity updateUserEntity(UserEntity userEntity, User user);
}
