package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }



    public boolean register1(Register register) {
        if (manager.userExists(register.getUserName())) {
            return false;
        }
        manager.createUser(
                org.springframework.security.core.userdetails.User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUserName())
                        .roles(register.getRole().name())
                        .build());
        return true;
    }

    @Override
    public boolean register (Register register) {
        if (manager.userExists(register.getUserName())) {
            return false;
        }

        User user = new User();
        user.setUserName(register.getUserName());
        user.setPassword(register.getPassword());
        user.setRole(register.getRole());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setEmail(register.getUserName());

        userRepository.save(user);
        return true;
    }

}
