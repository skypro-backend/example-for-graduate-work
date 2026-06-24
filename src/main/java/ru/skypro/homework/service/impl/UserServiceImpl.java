package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    public boolean setPassword(NewPassword newPassword, String username) {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (encoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(newPassword.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(String username) {
        UserEntity user = userRepository.findByEmail(username).orElseThrow();
        return userMapper.toDto(user);
    }

    @Override
    public UpdateUser updateUser(UpdateUser dto, String username) {
        UserEntity user = userRepository.findByEmail(username).orElseThrow();
        userMapper.updateEntity(dto, user);
        userRepository.save(user);
        return dto;
    }

    @Override
    public void updateUserImage(MultipartFile image, String username) {
        UserEntity user = userRepository.findByEmail(username).orElseThrow();
        try {
            String fileName = imageService.saveImage(image);
            user.setImage("/images/" + fileName);
            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения аватара", e);
        }
    }

}