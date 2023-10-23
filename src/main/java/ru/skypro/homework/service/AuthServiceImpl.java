package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {


    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(PasswordEncoder encoder, UserRepository userRepository, UserDetailsService userDetailsService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        if (userDetails == null) {
            return false;
        }

        return encoder.matches(password, userDetails.getPassword());
    }


    @Override
    public boolean register(Register register) {
        UserDetails existingUser = userDetailsService.loadUserByUsername(register.getUserName());

        if (existingUser != null) {
            return false; // Пользователь с таким именем уже существует
        }

        User user = new User();
        user.setUserName(register.getUserName());
        user.setPassword(register.getPassword());
        user.setRole(register.getRole());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setEmail(register.getUserName());

        userRepository.save(user);
        return true;
    }

    @Override
    public Optional<String> changePassword(String userName, String currentPassword, String newPassword) {
        // Проверяем, существует ли пользователь с заданным именем пользователя.
        User user = userRepository.findUserByUserName(userName);
        if (user == null) {
            return Optional.empty(); // Возвращаем Optional.empty(), если пользователь не найден.
        }

        // Проверяем, совпадает ли текущий пароль с паролем пользователя.
        if (encoder.matches(currentPassword, user.getPassword())) {
            return Optional.empty(); // Возвращаем Optional.empty(), если пароль неверный.
        }

        // Если все проверки пройдены, обновляем пароль пользователя
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);

        // Возвращаем новый пароль
        return Optional.of(newPassword);

    }

    @Override
    public User updateUserInfo(Long userId, UpdateUserDTO updateUserDTO) {
        // Найдем пользователя по userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Обновим информацию пользователя
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setPhone(updateUserDTO.getPhone());

        // Сохраните обновленного пользователя в базе данных
        return userRepository.save(user);
    }

}
