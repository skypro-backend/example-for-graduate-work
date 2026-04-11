package ru.skypro.homework.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

/**
 * Реализация сервиса пользовательских деталей для Spring Security.
 * Загружает пользователя по email, устанавливает роль и статус.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Загружает пользователя по email и возвращает объект UserDetails для Spring Security.
     * Генерирует роль и устанавливает статус аккаунта.
     *
     * @param email адрес электронной почты пользователя.
     * @return UserDetails объект с информацией о пользователе.
     * @throws UsernameNotFoundException если пользователь не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String roleName = Optional.ofNullable(user.getRole())
                .map(role -> role.getName())
                .orElse("USER");

        String authority = roleName.startsWith("ROLE_")
                ? roleName
                : "ROLE_" + roleName;

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(authority)
                .disabled(!Boolean.TRUE.equals(user.getEnabled()))
                .build();
    }
}
