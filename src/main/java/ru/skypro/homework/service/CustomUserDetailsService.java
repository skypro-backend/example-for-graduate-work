package ru.skypro.homework.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsManager {

    private final UserRepository userRepository;

    public CustomUserDetailsService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + username
                        )
                );

        return new User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRole().name()
                        )
                )
        );
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException(
                "Use AuthService for user registration"
        );
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException(
                "Use UserService for user updates"
        );
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException(
                "User deletion is not supported"
        );
    }

    @Override
    public void changePassword(
            String oldPassword,
            String newPassword
    ) {
        throw new UnsupportedOperationException(
                "Use UserService for password change"
        );
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
}