package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder,
                           ImageService imageService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    @Override
    public User getUser(String username) {
        log.debug("Getting user by username: {}", username);
        UserEntity userEntity = findUserByUsername(username);
        User user = userMapper.toUserDto(userEntity);
        // Добавляем полный URL для аватара
        if (userEntity.getImage() != null) {
            user.setImage(imageService.getImageUrl(userEntity.getImage()));
        }
        return user;
    }

    @Override
    @Transactional
    public UpdateUser updateUser(String username, UpdateUser updateUser) {
        log.debug("Updating user: {}", username);
        UserEntity userEntity = findUserByUsername(username);
        userMapper.updateUserEntity(updateUser, userEntity);
        userRepository.save(userEntity);
        return updateUser;
    }

    @Override
    @Transactional
    public boolean setPassword(String username, NewPassword newPassword) {
        log.debug("Changing password for user: {}", username);
        UserEntity userEntity = findUserByUsername(username);

        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        userEntity.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(userEntity);
        return true;
    }

    @Override
    @Transactional
    public String updateUserImage(String username, MultipartFile image) {
        log.debug("Updating user image for: {}", username);
        UserEntity userEntity = findUserByUsername(username);

        // Удаляем старую картинку, если она есть
        if (userEntity.getImage() != null) {
            imageService.deleteImage(userEntity.getImage());
        }

        // Сохраняем новую картинку
        String fileName = "user_" + userEntity.getId();
        String imagePath = imageService.saveImage(image, "users", fileName);

        userEntity.setImage(imagePath);
        userRepository.save(userEntity);

        return imageService.getImageUrl(imagePath);
    }

    private UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}