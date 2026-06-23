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

@Service
@RequiredArgsConstructor
public class MyUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;

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

    @Override
    public void createUser(UserDetails user) {
        // Этот метод будет вызываться из AuthService при регистрации
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getUsername());
        entity.setPassword(user.getPassword());
        // Извлекаем роль из UserDetails (обычно "ROLE_USER")
        String roleName = user.getAuthorities().iterator().next().getAuthority().substring(5);
        entity.setRole(Role.valueOf(roleName));
        userRepository.save(entity);
    }

    @Override public boolean userExists(String username) { return userRepository.findByEmail(username).isPresent(); }
    @Override public void updateUser(UserDetails user) {}
    @Override public void deleteUser(String username) {}
    @Override public void changePassword(String oldPassword, String newPassword) {}
}