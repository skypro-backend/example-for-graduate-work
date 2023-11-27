package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

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

import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.utils.MethodLog;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import java.io.IOException;



@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Value("${path.to.avatars.folder}")
    private String photoAvatar;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;

    }

    @Override
    public UserDTO getCurrentUser() {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        return UserMapper.INSTANCE.toUserDTO(user);

    }
    @Override
    public User getCurrentUser(String userName) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());
        return userRepository.findByEmail(userName);

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

    public String updateUserImage(MultipartFile image, String userName) {
          log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        User user = getCurrentUser(userName);
        Path filePath = Path.of(photoAvatar, user.getFirstName() + "." + getExtension(Objects.requireNonNull(image.getOriginalFilename())));
        uploadPhotoAdd(filePath,image);
        user.setImage(String.valueOf(filePath));
        return userRepository.save(user).getImage();
    }

    public void uploadPhotoAdd(Path filePath, MultipartFile image) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);

    }
}
