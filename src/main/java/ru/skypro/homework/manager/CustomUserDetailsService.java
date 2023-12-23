package ru.skypro.homework.manager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username); // Поиск пользователя в репозитории по его email (username)
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Возвращение объекта UserDetails, представляющего пользователя в Spring Security
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)             // Установка имени пользователя
                .password(user.getPassword())   // Установка пароля пользователя
                .roles(user.getRole().name())   // Установка роли пользователя (роль преобразуется в строку)
                .build();
    }
}

