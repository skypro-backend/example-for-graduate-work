package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.RegisterService;

import java.util.regex.Pattern;

@Service
public class RegisterServiceImpl implements RegisterService {

    private static final String PHONE_NUMBER_PATTERN = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}";

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    public RegisterServiceImpl(final UserDetailsManager manager, final PasswordEncoder encoder, final UserRepository userRepository) {
        this.manager = manager;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean registerUser(RegisterDto register) {
        if (manager.userExists(register.getUsername()) || !validateRegister(register)) {
            return false;
        }

        String password = encoder.encode(register.getPassword());

        User newUser = new User();
        newUser.setEmail(register.getUsername());
        newUser.setPassword(password);
        newUser.setFirstName(register.getFirstName());
        newUser.setLastName(register.getLastName());
        newUser.setPhone(register.getPhone());
        newUser.setRole(register.getRole());
        userRepository.save(newUser);

        return true;
    }

    public boolean validateRegister(RegisterDto register) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        boolean matcher = pattern.matcher(register.getPhone()).matches();

        return (register.getUsername().length() >= 4 && register.getUsername().length() <= 32)
               && (register.getPassword().length() >= 8 && register.getPassword().length() <= 16)
               && (register.getFirstName().length() >= 2 && register.getFirstName().length() <= 16)
               && (register.getLastName().length() >= 2 && register.getLastName().length() <= 16)
               && (matcher);
    }

}
