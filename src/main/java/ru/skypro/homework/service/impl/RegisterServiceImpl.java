package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.RegisterService;

import static ru.skypro.homework.dto.RegisterDto.validateRegister;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> registerUser(RegisterDto register) {
        if (!validateRegister(register)) {
            return ResponseEntity.badRequest().body("Неверные данные пользователя");
        }
        User newUser = new User();
        newUser.setUsername(register.getUsername());
        newUser.setPassword(register.getPassword());
        newUser.setFirstName(register.getFirstName());
        newUser.setLastName(register.getLastName());
        newUser.setPhone(register.getPhone());
        newUser.setRole(register.getRole());
        userRepository.save(newUser);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }
}
