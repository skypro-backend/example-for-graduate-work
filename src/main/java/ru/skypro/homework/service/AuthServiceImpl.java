package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDetailsDTO;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {


    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final UserService userService;

    public AuthServiceImpl(PasswordEncoder encoder, UserRepository userRepository, UserService userService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = userService.loadUserByUsername(userName);

        if (userDetails == null) {
            return false;
        }

        return encoder.matches(password, userDetails.getPassword());
    }


    @Override
    public boolean register(Register register) {
//        UserDetails existingUser = userService.loadUserByUsername(register.getUserName());
//
//        if (existingUser != null) {
//            return false; // Пользователь с таким именем уже существует
//        }

        User user = new User();
        user.setUserName(register.getUsername());
//        String encodedPassword = encoder.encode(register.getPassword());
//        user.setPassword(encodedPassword);
        user.setPassword(register.getPassword());
        user.setRole(register.getRole());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setEmail(register.getUsername());

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
        if (currentPassword.equals(user.getPassword())) {
            // Если пароль совпадает, обновляем пароль пользователя
            user.setPassword(newPassword);
            userRepository.save(user);

            // Возвращаем новый пароль
            return Optional.of(newPassword);
        } else {
            return Optional.empty(); // Возвращаем Optional.empty(), если пароль неверный.
        }

    }

    @Override
    public User updateUserInfo(Authentication authentication, UpdateUserDTO updateUserDTO) {
        // Извлеките имя пользователя из аутентификации
        String username = authentication.getName();

        // Найдем пользователя по имени
        User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }

        // Обновим информацию пользователя
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setPhone(updateUserDTO.getPhone());

        // Сохраните обновленного пользователя в базе данных
        return userRepository.save(user);
    }

}
