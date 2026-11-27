package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
        return new CustomUserDetails(user);
    }

    @Override
    @Transactional
    public void createUser(UserDetails userDetails) {
        if (userDetails instanceof CustomUserDetails) {
            userRepository.save(((CustomUserDetails) userDetails).getUserEntity());
        }
    }

    @Override
    @Transactional
    public void updateUser(UserDetails userDetails) {
        if (userDetails instanceof CustomUserDetails) {
            userRepository.save(((CustomUserDetails) userDetails).getUserEntity());
        }
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userRepository.findByEmail(username).ifPresent(userRepository::delete);
    }

    @Override
    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidPasswordException("Текущий пароль указан неверно");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }
}