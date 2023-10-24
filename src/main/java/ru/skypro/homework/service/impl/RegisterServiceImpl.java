package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.RegisterService;

import java.util.regex.Pattern;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    @Autowired
    public RegisterServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<String> registerUser(RegisterDto register) {
        if (!validateRegister(register)) {
            return ResponseEntity.badRequest().body("Неверные данные пользователя");
        }
        UserDto newUser = new UserDto();
        newUser.setFirstName(register.getFirstName());
        newUser.setLastName(register.getLastName());
        newUser.setPhone(register.getPhone());
        newUser.setRole(register.getRole());
        userRepository.save(newUser);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }

    public static boolean validateRegister(RegisterDto register) {

        Pattern pattern = Pattern.compile("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}");
        boolean matcher = pattern.matcher(register.getPhone()).matches();

        return (register.getUsername().length() >= 4 && register.getUsername().length() <= 32)
               && (register.getPassword().length() >= 8 && register.getPassword().length() <= 16)
               && (register.getFirstName().length() >= 2 && register.getFirstName().length() <= 16)
               && (register.getLastName().length() >= 2 && register.getLastName().length() <= 16)
               && (!matcher);
    }
}
