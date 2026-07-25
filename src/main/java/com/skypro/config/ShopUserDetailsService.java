package com.skypro.config;

import com.skypro.entity.UserEntity;
import com.skypro.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Реализация {@link UserDetailsService} для загрузки данных пользователя из базы данных.
 * <p>
 * Используется Spring Security для аутентификации: при попытке входа
 * вызывается метод {@link #loadUserByUsername(String)}, который находит
 * пользователя в БД и возвращает объект {@link UserDetails} с его ролями.
 * </p>
 */
@Service
public class ShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ShopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Загружает данные пользователя по имени (логину) для аутентификации.
     *
     * @param username имя пользователя (логин)
     * @return объект {@link UserDetails} с логином, паролем и ролью
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole().name())
                .build();
    }
}
