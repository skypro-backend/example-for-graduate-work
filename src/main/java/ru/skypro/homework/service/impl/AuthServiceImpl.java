package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.JavaKidsUserDetailsManager;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final /*UserDetailsManager*/
            JavaKidsUserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(JavaKidsUserDetailsManager manager,
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

    @Override
    public boolean register(RegisterDto register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        /*manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());
        Optional<ru.skypro.homework.model.User> o =
                userRepository.findByEmail(register.getUsername());
        if (o.isEmpty()) {return false;}
            ru.skypro.homework.model.User u = o.get();

        u = RegisterDtoToUserMapper.INSTANCE.registerDtoToUserMapper(register);
        u = userRepository.save(u);*/

        manager.createUser(register);

        return true;
    }

}
