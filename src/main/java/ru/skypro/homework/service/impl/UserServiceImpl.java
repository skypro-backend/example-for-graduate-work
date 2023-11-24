package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.utils.MethodLog;

import java.io.IOException;
import java.util.UUID;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository, ImageRepository imageRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public UserDTO getCurrentUser() {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        return UserMapper.INSTANCE.toUserDTO(user);
    }

    @Override
    public UserDTO updateUser(UpdateUserDTO updateUserDTO) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        UserMapper.INSTANCE.updateUserDTOToUser(updateUserDTO, user);
        return UserMapper.INSTANCE.toUserDTO(userRepository.save(user));
    }

    @Override
    public Void setPassword(NewPasswordDTO newPasswordDTO) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        if (user == null) {
            // Обработка случая, если пользователя не существует
            throw new UserNotFoundException("User not found");
        }

        // Проверка, что старый пароль совпадает
        if (!encoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {
            // Обработка случая, если старый пароль не совпадает
            throw new IncorrectPasswordException("Incorrect old password");
        }

        // Обновление пароля
        String hashedPassword = encoder.encode(newPasswordDTO.getNewPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return null;
    }

    @Override
    public Void updateUserImage(MultipartFile image) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Image entity = new Image();
        entity.setId(UUID.randomUUID().toString()); // генерируем уникальный идентификатор
        try {
            byte[] bytes = image.getBytes();
            entity.setImage(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setImage(entity.getId());
        imageRepository.save(entity);
        userRepository.save(user);
        return null;
    }
}
