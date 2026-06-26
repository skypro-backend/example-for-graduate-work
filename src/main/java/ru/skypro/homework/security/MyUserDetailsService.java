package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

/**
 * Кастомный сервис для загрузки данных пользователя при аутентификации.
 * Интегрирует Spring Security с нашей базой данных PostgreSQL.
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Ищет пользователя в базе данных по его логину (email).
     *
     * @param username логин (email) пользователя, вводимый в форме авторизации
     * @return объект {@link MyUserPrincipal}, содержащий данные пользователя и его права
     * @throws UsernameNotFoundException если пользователь с таким email не найден в БД
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new MyUserPrincipal(user);
    }
}