package ru.skypro.homework.service;

import ru.skypro.homework.dto.authentication.Register;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.entity.UserEntity;

public interface UserMapper {

    public UserEntity newPasswordDtoToUser(NewPassword newPassword);

    public User userEntityToUserDto(UserEntity userEntityInp);

    public UserEntity updateUserDtoToUserEntity(UpdateUser updateUser);

    public UserEntity registerDTOtoUserEntity (Register register);
}
