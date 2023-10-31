package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.authentication.Register;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.UserMapper;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity toUserEntity(Register register) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(register.getUsername());
        userEntity.setPassword(register.getPassword());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setPhone(register.getPhone());
        userEntity.setRole(register.getRole());
        return userEntity;
    }

    @Override
    public User toUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        user.setRole(userEntity.getRole());
        user.setImage("/users/image/" + userEntity.getId());
        return user;
    }

    @Override
    public UserEntity updateUserEntity(UserEntity userEntity, User user) {
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        return userEntity;
    }
}
