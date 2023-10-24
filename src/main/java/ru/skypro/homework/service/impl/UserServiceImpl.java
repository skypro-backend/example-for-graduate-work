package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

/**
 * Сервис для поддержки пользователей
 * {@link ImageService}
 * {@link PasswordEncoder}
 * {@link UserDetailsManager}
 * {@link UserRepository}
 * {@link UserMapper}
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final ImageService imageService;
    private final UserRepository repository;
    private final UserMapper mapper;

    /**
     * Получить информацию о пользователе из репозитория
     * {@link UserRepository}
     *
     * @param username String
     * @return {@link User}
     */
    private User find(String username) {
        return repository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Получить информацию о пользователе из автооризации
     * {@link SecurityContextHolder}
     *
     * @return {@link User}
     */
    @Override
    public User find() {
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return find(username);
    }

    /**
     * Создание пользователя
     */
    @Override
    public void createUser(RegisterDto registerDto) {
        var user = find(registerDto.getUsername());
        mapper.update(registerDto, user);
        repository.save(user);
    }

    /**
     * Чтение информации о пользователе
     */
    @Override
    public UserDto getUser() {
        var user = find();
        return mapper.userToUserDto(user);
    }

    /**
     *Редактирование пароля
     */
    @Override
    public void updatePassword(NewPasswordDto newPasswordDto) {
        var oldPassword = newPasswordDto.getCurrentPassword();
        var newPassword = encoder.encode(newPasswordDto.getNewPassword());
        manager.changePassword(oldPassword, newPassword);
    }

    /**
     * Обновление информации о пользователе
     */
    @Override
    public void updateUser(UpdateUserDto updateUserDto) {
        var user = find();
        mapper.update(updateUserDto, user);
        repository.save(user);
    }

    /**
     * Обновление информации о пользователе
     */
    @Override
    public void update(MultipartFile image) {
        var user = find();
        String filename;
        try {
            filename = imageService.create(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        user.setImage("/images/" + filename);
        repository.save(user);
    }
}