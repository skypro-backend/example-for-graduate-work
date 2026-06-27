package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

/**
 * Реализация {@link UserDetailsManager} для интеграции со Spring Security.
 * Позволяет загружать данные пользователя и регистрировать их через хранилище в БД.
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;

    /**
     * Загружает данные пользователя из базы данных для аутентификации Spring Security.
     *
     * @param username имя пользователя (email)
     * @return заполненный объект {@link UserDetails} с правами доступа
     * @throws UsernameNotFoundException если пользователь с указанным именем отсутствует в БД
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    /**
     * Создает нового пользователя на основе Spring {@link UserDetails} и сохраняет его в БД.
     * Метод извлекает логин, зашифрованный пароль и роли.
     *
     * @param user данные регистрируемого пользователя
     */
    @Override
    public void createUser(UserDetails user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getUsername());
        entity.setPassword(user.getPassword());
        String roleName = user.getAuthorities().iterator().next().getAuthority().substring(5);
        entity.setRole(Role.valueOf(roleName));
        userRepository.save(entity);
    }

    /**
     * Проверяет, зарегистрирован ли пользователь с указанным логином в БД.
     *
     * @param username логин пользователя (email)
     * @return {@code true}, если пользователь найден, иначе {@code false}
     */
    @Override
    public boolean userExists(String username) {
        return userRepository.findByEmail(username).isPresent();
    }

    @Override public void updateUser(UserDetails user) {}
    @Override public void deleteUser(String username) {}
    @Override public void changePassword(String oldPassword, String newPassword) {}
}