package ru.skypro.homework.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserAuthentication {
    private final UserRepository userRepository;

    /**
     * Приватный метод, который вытаскивает имя авторизованного пользователя
     * @return имя авторизованного пользователя
     */
    public UserEntity getCurrentUserName() {
        Authentication authenticationUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authenticationUser.getName());
    }
}
