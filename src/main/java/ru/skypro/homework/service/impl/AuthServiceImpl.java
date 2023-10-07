package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.account.Register;
import ru.skypro.homework.dto.account.Role;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserMapper;

import javax.annotation.PostConstruct;

import static ru.skypro.homework.dto.account.Role.USER;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder encoder,
                           UserMapper userMapper, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = encoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        UserEntity admin = new UserEntity();
        if (!manager.userExists(admin.getEmail())) {
            admin.setEmail("admin@gmail.com");
            admin.setPassword(encoder.encode("password"));
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setPhoneUser("+77777777777");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        Role role = (register.getRole() == null) ? USER : register.getRole();
        register.setRole(role);
        userRepository.save(userMapper.toUserEntity(register));
        return true;
    }
}
