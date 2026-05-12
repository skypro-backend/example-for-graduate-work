package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.controller.AuthController;
import ru.skypro.homework.repository.UserRepository;

/**
 * Реализация сервиса аутентификации пользователей на основе Spring Security.
 * <p>
 * Обеспечивает загрузку данных пользователя по email для последующей проверки учётных данных.
 * Интегрируется с {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}
 * и используется при входе в систему.
 * <p>
 * Основной метод — {@link #loadUserByUsername(String)} — вызывается автоматически
 * при попытке аутентификации через HTTP Basic или форму логина.
 *
 * @see UserDetailsService — интерфейс Spring Security для загрузки пользователя
 * @see UserRepository — источник данных о пользователях
 * @see AuthController#login — использует этот сервис для проверки логина/пароля
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Репозиторий для доступа к данным пользователей.
     */
    private final UserRepository userRepository;

    /**
     * Загружает пользователя по его email (используется как username).
     * <p>
     * Вызывается Spring Security автоматически при аутентификации.
     * Если пользователь не найден — выбрасывается исключение,
     * что приводит к ответу 401 Unauthorized.
     *
     * @param email логин пользователя (в системе используется email)
     * @return объект {@link UserDetails}, содержащий учётные данные и права доступа
     * @throws UsernameNotFoundException если пользователь с таким email не существует
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}