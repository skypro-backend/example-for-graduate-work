package ru.skypro.homework.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepo;

@Component
public class Util {
    @Autowired
    private UserRepo userRepo;

    public UserModel addUserFromRepo(Authentication authentication) {
        return userRepo.findByUserName(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
    }
}
