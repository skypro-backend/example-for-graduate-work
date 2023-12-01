package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Этот метод переопределен из интерфейса UserDetailsManager
     * для настройки процесса аутентификации для Spring Security.
     * Он извлекает данные пользователя, включая имя пользователя, пароль и роль,
     * для создания объекта UserDetails.
     * Метод использует {@link UserRepository#findByEmail(String)}
     * @param username - логин пользователя
     * @return - объект UserDetails, содержащий сведения о пользователе
     * @throws UsernameNotFoundException - исключение
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
