package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.Exceptions.UserNotFoundException;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param newPasswordDto {@link NewPasswordDto} объект, содержащий текущий и новый пароли
     * @param authentication {@link Authentication}
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    @Override
    public boolean setPassword(NewPasswordDto newPasswordDto, Authentication authentication) {
        log.info("Внутри метода SetPassword");
        try {
            User user = userRepository
                    .findByEmail(authentication.name())
                    .orElseThrow(UserNotFoundException::new);

            if (!passwordEncoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
                throw new RuntimeException("Не совпадают пароли");
            }
            user.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
            log.info("Установка нового пароля");
            userRepository.save(user);
        } catch (Exception e) {
            log.warn("Не удалось изменить пароль: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Получение информации об зарегистрированном пользователе
     *
     * @param authentication {@link Authentication}
     * @return Объект {@link UserDto}
     */
    @Override
    public UserDto getUser(Authentication authentication) {
        User user = userRepository
                .findByEmail(authentication.name())
                .orElseThrow(UserNotFoundException::new);
        log.info("Запрошенная информация: " + userMapper.toUserDto(user));
        return userMapper.toUserDto(user);
    }

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param userDto        новая информация об пользователе
     * @param authentication {@link Authentication}
     * @return {@link UserDto} обновленные данные, в случае успешного изменения
     */
    @Override
    public UserDto updateUser(UserDto userDto, Authentication authentication) {
        User authenticatedUser = userRepository
                .findByEmail(authentication.name())
                .orElseThrow(UserNotFoundException::new);
        if (userDto.getFirstName() != null && !userDto.getFirstName().isBlank()) {
            authenticatedUser.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null && !userDto.getLastName().isBlank()) {
            authenticatedUser.setLastName(userDto.getLastName());
        }
        if (userDto.getPhone() != null && !userDto.getPhone().isBlank()) {
            authenticatedUser.setPhone(userDto.getPhone());
        }
        log.info(authenticatedUser);
        return userMapper.toUserDto(userRepository.save(authenticatedUser));

    }

    /**
     * Импортирует изображение для аватарки зарегистрированном пользователя
     *
     * @param imageFile      объект {@link MultipartFile}
     * @param authentication {@link Authentication}
     * @return <code>true</code> если изображение загружено, <code>false</code> в случае неудачи
     */
    @Override
    public boolean updateUserImage(MultipartFile imageFile, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.name())
                .orElseThrow(UserNotFoundException::new);
        Image image;
        try {
            image = imageService.downloadImage(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        user.setImage(image);
        userRepository.saveAndFlush(user);
        return true;
    }
}
