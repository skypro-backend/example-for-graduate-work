package ru.skypro.homework.service.auth.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.service.auth.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        if (manager.userExists(registerDto.getUsername())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(registerDto.getPassword())
                        .username(registerDto.getUsername())
                        .roles(registerDto.getRole().name())
                        .build());
        return true;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }



    /**
     * Create a new user with the supplied details.
     */
    void createUser(UserDetails user);

    /**
     * Update the specified user.
     */
    void updateUser(UserDetails user);

    /**
     * Remove the user with the given login name from the system.
     */
    void deleteUser(String username);

    /**
     * Modify the current user's password. This should change the user's password in the
     * persistent user repository (datbase, LDAP etc).
     * @param oldPassword current password (for re-authentication if required)
     * @param newPassword the password to change to
     */
    void changePassword(String oldPassword, String newPassword);

    /**
     * Check if a user with the supplied login name exists in the system.
     */
    boolean userExists(String username);

}
