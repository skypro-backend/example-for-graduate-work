package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;

/**
 * Реализация сервиса для загрузки данных пользователя в Spring Security.
 * Используется для аутентификации пользователей по email.
 * Основные функции:
 * - загрузка пользователя по имени (email) из базы данных;
 * - преобразование сущности пользователя в объект UserDetails для Spring Security.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Загрузить данные пользователя по email.
     * @param username email пользователя
     * @return объект {@link UserDetails} для Spring Security
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
