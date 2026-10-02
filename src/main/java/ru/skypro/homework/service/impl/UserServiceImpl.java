package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.api.dto.NewPassword;
import ru.skypro.homework.api.dto.UpdateUser;
import ru.skypro.homework.api.dto.User;
import ru.skypro.homework.db.model.UserModel;
import ru.skypro.homework.db.repository.UserRepository;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.ImageStorageService;
import ru.skypro.homework.service.StoredImage;
import ru.skypro.homework.service.UserService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_IMAGE_PATH = "/users/%d/image";

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageStorageService imageStorageService;

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser(String username) {
        return userMapper.toDto(getCurrentUserModel(username));
    }

    @Override
    @Transactional(readOnly = true)
    public UserModel getCurrentUserModel(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    @Transactional
    public UpdateUser updateCurrentUser(String username, UpdateUser updateUser) {
        UserModel user = getCurrentUserModel(username);
        userMapper.updateModel(updateUser, user);
        return userMapper.toUpdateUser(userRepository.save(user));
    }

    @Override
    @Transactional
    public void setPassword(String username, NewPassword newPassword) {
        UserModel user = getCurrentUserModel(username);
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new ResponseStatusException(BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserImage(String username, MultipartFile image) {
        UserModel user = getCurrentUserModel(username);
        String imageFileName = imageStorageService.save(image);
        user.setImage(buildImagePath(user.getId(), imageFileName));
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public StoredImage getUserImage(Integer id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        return imageStorageService.read(getImageFileName(user));
    }

    private String buildImagePath(Integer userId, String imageFileName) {
        return String.format(USER_IMAGE_PATH, userId) + "?file=" + imageFileName;
    }

    private String getImageFileName(UserModel user) {
        String imagePath = user.getImage();
        if (imagePath == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        int fileNameStart = imagePath.indexOf("?file=");
        if (fileNameStart < 0) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return imagePath.substring(fileNameStart + "?file=".length());
    }
}
