package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.account.NewPassword;
import ru.skypro.homework.dto.account.User;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AccountService;
import ru.skypro.homework.service.UserMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetails userDetails;
    private final UserDetailsManager userDetailsManager;

    public AccountServiceImpl(UserRepository userRepository, UserMapper userMapper, UserDetails userDetails, UserDetailsManager userDetailsManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userDetails = userDetails;
        this.userDetailsManager = userDetailsManager;
    }

    private static final String USER_NOT_FOUND = "User not found";

    @Value("${users.avatar.dir.path}")
    private String avatarsDir;

    @Override
    @Transactional
    public boolean updatePassword(NewPassword newPassword) {
        if (newPassword != null &&
                newPassword.getNewPassword() != null &&
                !newPassword.getNewPassword().isEmpty() &&
                !newPassword.getNewPassword().isBlank()) {
            userDetailsManager.changePassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public User getInfoAboutUser() {
        String userName = userDetails.getUsername();
        return userMapper.toUser(userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND)));
    }

    @Override
    @Transactional
    public User patchInfoAboutUser(User user) {
        String userName = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        UserEntity updatedUser = userRepository.save(userMapper.updateUserEntity(userEntity, user));
        return userMapper.toUser(updatedUser);
    }

    @Override
    @Transactional
    public boolean updateUserAvatar(MultipartFile image) throws IOException {
        String userName = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        if (userEntity.getImagePath() != null) {
            Files.deleteIfExists(Path.of(userEntity.getImagePath()));
        }
        Path filePath = Path.of(avatarsDir, userEntity.getId() + "."
                + StringUtils.getFilenameExtension(image.getOriginalFilename()));
        uploadImage(image, filePath);
        userEntity.setImagePath(filePath.toAbsolutePath().toString());
        userRepository.save(userEntity);
        return true;
    }

    static void uploadImage(MultipartFile image, Path filePath) throws IOException {
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
}
