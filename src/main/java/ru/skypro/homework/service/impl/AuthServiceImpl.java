package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.repository.AuthoritiesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder,
                           UserService userService, UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userService = userService;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public boolean login(String username, String password) {
        if (!manager.userExists(username)) {
            return false;
        }
    UserDetails userDetails = manager.loadUserByUsername(username);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .password(encoder.encode(register.getPassword()))
                .username(register.getUsername())
                .roles(Role.USER.name())
                .build();

        manager.createUser(userDetails);

        UpdateUserDto userDto = new UpdateUserDto();
        userDto.setFirstName(register.getFirstName());
        userDto.setLastName(register.getLastName());
        userDto.setPhone(register.getPhone());

        userService.updateUser(register.getUsername(), userDto);
        return true;
    }
}
