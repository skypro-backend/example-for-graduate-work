package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.RoleEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.RoleRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

/**
 * Реализация сервиса для аутентификации и регистрации пользователей.
 * Обеспечивает вход по электронной почте и паролю, а также регистрацию новых пользователей с назначением ролей.
 * Использует шифрование паролей и проверку существующих пользователей.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public boolean login(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> encoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.findByEmail(register.getUsername()).isPresent()) {
            return false;
        }

        String roleName = register.getRole() == null ? "USER" : register.getRole().name();
        RoleEntity role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Роль " + roleName + " не найдена"));

        UserEntity user = new UserEntity();
        user.setEmail(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setRole(role);
        user.setEnabled(true);

        userRepository.save(user);

        return true;
    }
}