package ru.skypro.homework.service.mapping;

import ru.skypro.homework.dto.authentication.ExtendedLoginViaDB;
import ru.skypro.homework.dto.authentication.Register;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.entity.UserEntity;

public interface UserMapper {

    public UserEntity newPasswordDtoToUser(NewPassword newPassword);

    public ru.skypro.homework.dto.user.User userEntityToUserDto(UserEntity userInp);

    public UserEntity updateUserDtoToUserEntity(UpdateUser updateUser);

    public UserEntity registerDTOtoUserEntity (Register register);

    public ExtendedLoginViaDB extendedLoginViaDB (UserEntity user);
}
