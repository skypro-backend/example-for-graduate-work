package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.constants.ErrorMessages;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsManager implements UserDetailsManager {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {


        User user =
                userRepository.findByEmail(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        ErrorMessages.USER_NOT_FOUND
                                )
                        );


        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }


    @Override
    public void createUser(UserDetails user) {

        throw new UnsupportedOperationException();
    }


    @Override
    public void updateUser(UserDetails user) {

        throw new UnsupportedOperationException();
    }


    @Override
    public void deleteUser(String username) {

        throw new UnsupportedOperationException();
    }

    private String getCurrentUsername() {

        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }


    @Override
    public void changePassword(
            String oldPassword,
            String newPassword
    ) {

        User user =
                userRepository.findByEmail(
                                getCurrentUsername()
                        )
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        ErrorMessages.USER_NOT_FOUND
                                )
                        );

        user.setPassword(newPassword);

        userRepository.save(user);
    }


    @Override
    public boolean userExists(String username) {

        return userRepository.findByEmail(username)
                .isPresent();
    }
}
