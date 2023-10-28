package ru.skypro.homework.service.util;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepo;

public class Util {

    private static UserRepo userRepo;

    public static UserModel addUserFromRepo(Authentication authentication) {
        UserModel user = userRepo.findByUserName(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        return user;
    }
}
