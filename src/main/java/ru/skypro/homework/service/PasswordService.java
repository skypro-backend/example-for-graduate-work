package ru.skypro.homework.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.model.User;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean updatePassword(User user, NewPasswordDto newPasswordDto) {
        String currentPassword = newPasswordDto.getCurrentPassword();
        String newPassword = newPasswordDto.getNewPassword();

        // Проверяем, соответствует ли текущий пароль текущему паролю пользователя
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        // Если текущий пароль соответствует, обновляем пароль
        user.setPassword(passwordEncoder.encode(newPassword));
        return true;
    }
}
