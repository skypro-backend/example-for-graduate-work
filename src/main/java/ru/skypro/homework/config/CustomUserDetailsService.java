package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    //репозиторий пользователей
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(userInfo.getEmail())
                .password(userInfo.getPassword())
                .roles(userInfo.getRole().name())
                .build();
    }
}
