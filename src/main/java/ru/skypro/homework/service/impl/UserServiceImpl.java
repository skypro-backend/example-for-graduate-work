package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.UserMapper;

import javax.swing.*;
/**
 * Класс-сервис, реализующий интерфейс {@link UserInfo}
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthServiceImpl authService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Метод для смены пароля
     * @param newPassword
     */
    @Override
    @Transactional
    public void setPassword(NewPasswordDTO newPassword) {
        UserInfo user = authService.getCurrentUser();
        if (!encoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        String encodedNewPassword = encoder.encode(newPassword.getNewPassword());
        user.setPassword(encodedNewPassword);

        logger.info("Пароль успешно изменен");
        userRepository.save(user);
    }

    /**
     * Метод возвращающает информацию о пользователе
     * @return {@link UserDTO}
     */
    @Override
    @Transactional
    public UserDTO getUserInfo() {
        UserInfo user = authService.getCurrentUser();
        return userMapper.userToDto(user);
    }

    /**
     * Метод обновляет информация о пользователе
     * @param updateUser
     * @return {@link UpdateUserDTO}
     */
    @Override
    @Transactional
    public UpdateUserDTO updateUser(UpdateUserDTO updateUser) {
        UserInfo user = authService.getCurrentUser();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());

        logger.info("Информация о пользователе обновлена");
        return userMapper.userToUpdateUserDto(user);
    }

    /**
     * Метод обновляет изображение пользователя
     * @param image
     */
    @Override
    @Transactional
    public void updateUserImage(MultipartFile image) {
        UserInfo user = authService.getCurrentUser();
        Image uploadImage = imageService.uploadImage(image);
        user.setImage(uploadImage);
        userRepository.save(user);

        logger.info("Изображение обновлено");
    }

    /**
     * Метод возвращает изображение пользователя
     * @param id
     * @return byte[]
     */
    @Override
    @Transactional
    public byte[] getImage(String id) {
        return imageService.getImage(id);
    }
}
