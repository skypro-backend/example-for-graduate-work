package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
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
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    private static final String UPLOAD_DIR = "images/";

    /**
     * Смена пароля (только сам пользователь или ADMIN)
     */
    @PreAuthorize("#email == authentication.name or hasRole('ADMIN')")
    @Override
    public void changePassword(NewPassword newPassword, String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Неверный текущий пароль");
        }

        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
    }

    /**
     * Получение текущего пользователя
     */
    @PreAuthorize("#email == authentication.name or hasRole('ADMIN')")
    @Override
    public User getCurrentUser(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return userMapper.toDto(user);
    }

    /**
     * Обновление профиля
     */
    @PreAuthorize("#email == authentication.name or hasRole('ADMIN')")
    @Override
    public UpdateUser updateUser(UpdateUser updateUser, String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

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

        return UpdateUser.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .build();
    }

    /**
     * Обновление аватара
     */
    @PreAuthorize("#email == authentication.name or hasRole('ADMIN')")
    @Override
    public void updateUserImage(MultipartFile file, String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (file.isEmpty()) {
            throw new RuntimeException("Файл пустой");
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File destination = new File(UPLOAD_DIR + fileName);

        try {
            file.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки файла");
        }

        user.setImage("/images/" + fileName);
        userRepository.save(user);
    }

    /**
     * Регистрация пользователя
     */
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
    }
}