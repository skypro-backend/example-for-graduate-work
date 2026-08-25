package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    @Override
    public void setPassword(NewPassword newPassword, String username) {
        manager.changePassword(newPassword.getCurrentPassword(), encoder.encode(newPassword.getNewPassword()));
    }

    @Override
    public User getUser(String username) {
        UserEntity entity = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        return userMapper.toDto(entity);
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, String username) {
        UserEntity entity = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        userMapper.updateUserFromDto(updateUser, entity);
        userRepository.save(entity);
        return updateUser;
    }

    @Override
    public void updateUserImage(MultipartFile image, String username) {
        UserEntity entity = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        // Временная симуляция сохранения пути картинки
        entity.setImage("/users/me/image");
        userRepository.save(entity);
    }
}