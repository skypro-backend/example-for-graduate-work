package ru.skypro.homework.service.mapping.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.authentication.ExtendedLoginViaDB;
import ru.skypro.homework.dto.authentication.Register;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.mapping.UserMapper;
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity newPasswordDtoToUser(NewPassword newPassword) {
        if (newPassword == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setPassword(newPassword.getNewPassword());
        return user;
    }

    @Override
    public ru.skypro.homework.dto.user.User userEntityToUserDto(UserEntity userInp) {
        if (userInp == null) {
            return null;
        }
        ru.skypro.homework.dto.user.User user = new ru.skypro.homework.dto.user.User();
        user.setId(userInp.getId());
        user.setFirstName(userInp.getFirstName());
        user.setLastName(userInp.getLastName());
        user.setPhone(userInp.getPhone());
        user.setRole(userInp.getRole());
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
        UserEntity user = new UserEntity();
        user.setUsername(register.getUsername());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPassword(register.getPassword());
        user.setPhone(register.getPhone());
        user.setRole(register.getRole());
        return user;
    }

    @Override
    public ExtendedLoginViaDB extendedLoginViaDB(UserEntity user) {
        if (user == null) {
            return null;
        }

        ExtendedLoginViaDB extendedLoginViaDB = new ExtendedLoginViaDB();
        extendedLoginViaDB.setPassword(user.getPassword());
        extendedLoginViaDB.setUsername(user.getUsername());
        extendedLoginViaDB.setRole(user.getRole());
        return extendedLoginViaDB;
    }
}

