package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    private final ImageService imageService;
    private final UserRepository repository;
    private final UserMapper mapper;private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User find() {
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return find(username);
    }

    private User find(String username) {
        return repository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public void createUser(RegisterDto registerDto) {
        var user = find(registerDto.getUsername());
        mapper.update(registerDto, user);
        try {
            repository.save(user);
            logger.info("Пользователь успешно сохранен.", user.getUsername());
        } catch (Exception e) {
            logger.error("Не удалось сохранить пользователя{}: {}", user.getUsername(), e.getMessage());
            throw new RuntimeException("Не удалось сохранить пользователя.", e);
        }
    }
    @Override
    public UserDto getUser() {
        var user = find();
        return mapper.userToUserDto(user);
    }

    @Override
    public void updatePassword(NewPasswordDto newPasswordDto) {
        var oldPassword = newPasswordDto.getCurrentPassword();
        var newPassword = encoder.encode(newPasswordDto.getNewPassword());
        var user = find();
        UserDetailsManager userDetailsManager = null;
        if (userDetailsManager.userExists(user.getUsername())) {
            userDetailsManager.changePassword(oldPassword, newPassword);
        } else {
            throw new IllegalArgumentException("Неверный текущий пароль");

        }
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto) {
        var user = find();
        mapper.update(updateUserDto, user);
        repository.save(user);
    }

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
