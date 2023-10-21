package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.RegisterService;

import static ru.skypro.homework.dto.Register.validateRegister;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> registerUser(Register register) {
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
