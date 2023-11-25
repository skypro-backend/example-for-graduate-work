package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthServiceImpl authService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;

    @Override
    public void setPassword(NewPasswordDTO newPassword) {
        UserInfo user = authService.getCurrentUser();
        if (!encoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        String encodedNewPassword = encoder.encode(newPassword.getNewPassword());
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserInfo() {
        UserInfo user = authService.getCurrentUser();
        UserDTO userDTO = userMapper.userToDto(user);
        if (user.getImageModel() != null) {
            userDTO.setImage("/users/image/" + user.getImageModel().getId());
        }
        return userDTO;
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUser) {
        UserInfo user = authService.getCurrentUser();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());

        return userMapper.userToUpdateUserDto(user);
    }

    @Override
    @Transactional
    public void updateUserImage(MultipartFile image) {
        UserInfo user = authService.getCurrentUser();
        Image uploadImage = imageService.uploadImage(image);
        user.setImageModel(uploadImage);
        userRepository.save(user);

    }

    @Override
    @Transactional
    public byte[] getImage(String id) {
        return imageService.getImage(id);
    }
}
