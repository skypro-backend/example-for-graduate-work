package ru.skypro.kakavito.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.kakavito.dto.Register;
import ru.skypro.kakavito.model.User;
import ru.skypro.kakavito.repository.UserRepo;
import ru.skypro.kakavito.service.RegService;

import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class RegServiceImpl implements RegService {

    private static final String PHONE_NUMBER_PATTERN = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}";;
    private final PasswordEncoder encoder;
    private final UserRepo userRepo;


    @Override
    public boolean register(Register register) {
        User foundUser = userRepo.findByEmail(register.getUsername()).orElse(null);
        if (foundUser != null || !validateRegister(register)) {
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
        userRepo.save(newUser);
        return true;
    }

    private boolean validateRegister(Register register) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        boolean matcher = pattern.matcher(register.getPhone()).matches();

        return (register.getUsername().length() >= 4 && register.getUsername().length() <= 32)
                && (register.getPassword().length() >= 8 && register.getPassword().length() <= 16)
                && (register.getFirstName().length() >= 2 && register.getFirstName().length() <= 16)
                && (register.getLastName().length() >= 2 && register.getLastName().length() <= 16)
                && (matcher);
    }
}
