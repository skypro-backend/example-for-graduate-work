package ru.skypro.homework.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserAuthentication {
    private final UserRepository userRepository;

    public User getCurrentUserName() {
        return null;
    }

}
