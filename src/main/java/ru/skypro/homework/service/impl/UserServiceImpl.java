package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.mapping.UserMapper;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;


@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.imageRepository = imageRepository;
    }

    @Override
    public boolean changePassword(NewPassword newPassword, String userName) {
        UserEntity authorizedUser = userRepository.findByUsername(userName);
        if (passwordEncoder.matches(newPassword.getCurrentPassword(), authorizedUser.getPassword())) {
            authorizedUser.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            userRepository.save(authorizedUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ru.skypro.homework.dto.user.User retrieveAuthorizedInformation(String userName) {
        UserEntity authorizedUser = userRepository.findByUsername(userName);
        return userMapper.userEntityToUserDto(authorizedUser);
    }

    @Override
    public UpdateUser patchAuthorizedUserInformation(UpdateUser updateUser, String UserName) {
        UserEntity authorizedUser = userRepository.findByUsername(UserName);
        authorizedUser.setFirstName(updateUser.getFirstName());
        authorizedUser.setLastName(updateUser.getLastName());
        authorizedUser.setPhone(updateUser.getPhone());
        userRepository.save(authorizedUser);
        return updateUser;
    }

    @Override
    public void patchAuthorizedUserPicture(MultipartFile image, String username) {
        UserEntity authorizedUser = userRepository.findByUsername(username);

        try {
            byte[] imageBytes = image.getBytes();
            Image multipartToEntity = new Image();
            multipartToEntity.setImage(imageBytes);
            imageRepository.save(multipartToEntity);
            authorizedUser.setImageAvatar(multipartToEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userRepository.save(authorizedUser);
    }
}
