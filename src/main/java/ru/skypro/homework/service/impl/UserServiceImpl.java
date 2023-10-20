package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.WrongCurrentPasswordException;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final Users users = new Users(1,
            "user@gmail.com",
            "path-for-image",
            "user@gmail.com",
            "password",
            "ivan",
            "ivanov",
            "+7(777)-777-77-77",
            Role.USER);


    @Override
    public void updatePassword(NewPassword newPassword) {
        if (!newPassword.getCurrentPassword().equals(users.getPassword())) {
            throw new WrongCurrentPasswordException();
        }
    }

    @Override
    public User getInformation() {
        return User.toUser(users);
    }

    @Override
    public UpdateUser updateInformationAboutUser(UpdateUser updateUser) {
        return UpdateUser.toUpdateUser(users);
    }

    @Override
    public void UpdateImage(String image) {
//        users.setImage(image);
    }
}