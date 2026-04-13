package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        log.debug("Getting user by email: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public User updateUser(String email, UpdateUser updateUser) {
        log.info("Updating user: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        if (updateUser.getFirstName() != null && !updateUser.getFirstName().isEmpty()) {
            user.setFirstName(updateUser.getFirstName());
        }
        if (updateUser.getLastName() != null && !updateUser.getLastName().isEmpty()) {
            user.setLastName(updateUser.getLastName());
        }
        if (updateUser.getPhone() != null && !updateUser.getPhone().isEmpty()) {
            user.setPhone(updateUser.getPhone());
        }

        UserEntity saved = userRepository.save(user);
        log.info("User updated successfully: {}", email);

        return userMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void updateUserImage(String email, MultipartFile image) {
        log.info("Updating avatar for user: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        if (user.getImage() != null && !user.getImage().isEmpty()) {
            imageService.deleteImage(user.getImage());
        }

        String imagePath = imageService.saveAvatarImage(image, email);

        user.setImage(imagePath);
        userRepository.save(user);

        log.info("Avatar updated successfully for user: {}", email);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getUserImage(String email) {
        log.debug("Getting avatar for user: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        return imageService.getImage(user.getImage());
    }
}