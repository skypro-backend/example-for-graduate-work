package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.RoleEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.RoleRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

/**
 * Реализация сервиса пользователя.
 * Обеспечивает управление профилем пользователя, его паролем, изображением и регистрацией.
 * Включает операции обновления данных пользователя, смены пароля и аватара с проверками авторизации.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final ImageService imageService;

    @PreAuthorize("#email == authentication.name or hasRole('ADMIN')")
    @Override
    public void changePassword(NewPassword newPassword, String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));

        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный текущий пароль");
        }

        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);

        log.info("Пароль изменен для пользователя: {}", email);
    }

    @PreAuthorize("#email == authentication.name or hasRole('ADMIN')")
    @Override
    public User getCurrentUser(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));

        return userMapper.toDto(user);
    }

    @PreAuthorize("#email == authentication.name or hasRole('ADMIN')")
    @Override
    public UpdateUser updateUser(UpdateUser updateUser, String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));

        if (updateUser.getFirstName() != null) {
            user.setFirstName(updateUser.getFirstName());
        }

        if (updateUser.getLastName() != null) {
            user.setLastName(updateUser.getLastName());
        }

        if (updateUser.getPhone() != null) {
            user.setPhone(updateUser.getPhone());
        }

        userRepository.save(user);

        log.info("Профиль обновлен для пользователя: {}", email);

        return UpdateUser.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .build();
    }

    @PreAuthorize("#email == authentication.name or hasRole('ADMIN')")
    @Override
    public void updateUserImage(MultipartFile file, String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));

        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Файл пустой");
        }

        if (user.getImage() != null && !user.getImage().isEmpty()) {
            imageService.deleteImage(user.getImage());
        }

        String imageUrl = imageService.saveImage(file);
        user.setImage(imageUrl);

        userRepository.save(user);
        log.info("Аватар обновлен для пользователя: {}, путь: {}", email, imageUrl);
    }

    public void registerUser(User userDto, String password) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с таким email уже существует");
        }

        UserEntity userEntity = userMapper.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setEnabled(true);

        RoleEntity role = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new RoleEntity(null, "USER")));

        userEntity.setRole(role);
        userRepository.save(userEntity);

        log.info("Зарегистрирован новый пользователь: {}", userDto.getEmail());
    }
}