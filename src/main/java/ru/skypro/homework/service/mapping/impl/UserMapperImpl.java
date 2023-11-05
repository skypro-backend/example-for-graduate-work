package ru.skypro.homework.service.mapping.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.authentication.Register;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.mapping.UserMapper;
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity newPasswordDtoToUser(NewPassword newPassword) {
        if (newPassword == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(newPassword.getNewPassword());
        return userEntity;
    }

    @Override
    public User userEntityToUserDto(UserEntity userEntityInp) {
        if (userEntityInp == null) {
            return null;
        }
        User user = new User();
        user.setId(userEntityInp.getId());
        user.setFirstName(userEntityInp.getFirstName());
        user.setLastName(userEntityInp.getLastName());
        user.setPhone(userEntityInp.getPhone());
        user.setRole(userEntityInp.getRole());
        return user;
    }

    @Override
    public UserEntity updateUserDtoToUserEntity(UpdateUser updateUser) {
        if (updateUser == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(user.getPhone());
        return user;
    }

    @Override
    public UserEntity registerDTOtoUserEntity(Register register) {
        if (register == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(register.getUsername());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setPassword(register.getPassword());
        userEntity.setPhone(register.getPhone());
        userEntity.setRole(register.getRole());
        return userEntity;
    }
}
