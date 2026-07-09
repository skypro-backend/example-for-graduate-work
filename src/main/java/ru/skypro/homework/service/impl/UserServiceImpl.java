package ru.skypro.homework.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import static ru.skypro.homework.dto.Role.ADMIN;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, ImageService imageService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    @Override
    public void register(Register register) {
        UserEntity userEntity = userMapper.toUser(register);
        userEntity.setPassword(passwordEncoder.encode(register.getPassword()));

        if (userEntity.getRole() == null) {
            userEntity.setRole(Role.USER);
        }
        userRepository.save(userEntity);

    }

    @Override
    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void changePassword(Authentication authentication, NewPassword newPassword) {
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Неверный логин или пароль");
        }
        String encryptedPassword = passwordEncoder.encode(newPassword.getNewPassword());
        userEntity.setPassword(encryptedPassword);
        userRepository.save(userEntity);

    }

    @Override
    public User getUser(Authentication authentication){
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return userMapper.toUserDto(userEntity);
    }

    @Override
    public void updateUser(Authentication authentication, UpdateUser updateUser) {
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        userMapper.updateUserFromDto(userEntity, updateUser);
        userRepository.save(userEntity);

    }

    @Override
    public void updateUserImage(Authentication authentication, MultipartFile image) {
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        if (userEntity.getImage() != null && !userEntity.getImage().isBlank()) {
            imageService.deleteImage(userEntity.getImage());
        }
        String newImageName = imageService.saveImage(image);
        userEntity.setImage(newImageName);
        UserEntity updatedAUserEntity = userRepository.save(userEntity);
        userMapper.toUserDto(updatedAUserEntity);
    }


    /**
     * Метод-обёртка для контроллера
     * вызывает updateUserImage(Authentication authentication, MultipartFile image),
     * а потом загружает и возвращает картинку
     */
    @Override
    public Resource updateUserImageAndReturn(Authentication authentication, MultipartFile image) {
        updateUserImage(authentication, image);
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return imageService.getImage(userEntity.getImage());
    }
}



