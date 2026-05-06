package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.request.NewPasswordRequestDto;
import ru.skypro.homework.dto.request.UpdateUserRequestDto;
import ru.skypro.homework.dto.response.UserResponseDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.BadRequestException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CurrentUserService;
import ru.skypro.homework.service.FileStorageService;
import ru.skypro.homework.service.UserService;

/**
 * Сервис управления пользователем.
 * Отвечает за:
 * - изменение пароля,
 * - обновление профиля,
 * - загрузку аватара.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final FileStorageService fileStorageService;
    private final CurrentUserService currentUserService;

    /**
     * Изменение пароля пользователя.
     * @throws BadRequestException если текущий пароль неверный
     */
    @Override
    public void setPassword(NewPasswordRequestDto dto) {
        User user = currentUserService.getCurrentUser();
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("Incorrect current password");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getCurrentUser() {
        User user = currentUserService.getCurrentUser();
        return userMapper.userToDto(user);
    }

    /**
     * Обновление данных профиля пользователя.
     */
    @Override
    public UserResponseDto updateUser(UpdateUserRequestDto dto) {
        User user = currentUserService.getCurrentUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        User updatedUser = userRepository.save(user);
        return userMapper.userToDto(updatedUser);
    }

    /**
     * Обновление аватара пользователя.
     */
    @Override
    public void updateUserImage(MultipartFile image) {
        User user = currentUserService.getCurrentUser();
        String imagePath = fileStorageService.saveUserImage(user.getId(), image);
        user.setImage(imagePath);
        userRepository.save(user);
    }
}
