package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.db.model.UserModel;
import ru.skypro.homework.db.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DatabaseUserDetailsService implements UserDetailsManager, UserDetailsPasswordService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException("Use AuthService for user registration");
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("Use UserService for user updates");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("User deletion is not supported");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Use UserService for password updates");
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        UserModel userModel = userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + user.getUsername()));
        userModel.setPassword(newPassword);
        userRepository.save(userModel);
        return loadUserByUsername(user.getUsername());
    }
}
