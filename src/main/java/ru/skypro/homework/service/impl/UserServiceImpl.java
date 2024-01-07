package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.UserDetailsManagerImpl;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;


@AllArgsConstructor
@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final UserDetailsManagerImpl manager;


    @Override
    public UserDto getUserDetails() {
        return null;
    }

    @Override
    public UserDto getUser(Authentication authentication) {
        return UserMapper.mapToUserDto(userRepository.findByEmail(authentication.getName()).
                orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto updateUser(UpdateUserDto newProperties, Authentication authentication) {
        User updatedUser = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
        Optional.ofNullable(newProperties.getFirstName()).ifPresent(updatedUser::setFirstName);
        Optional.ofNullable(newProperties.getLastName()).ifPresent(updatedUser::setLastName);
        Optional.ofNullable(newProperties.getPhone()).ifPresent(updatedUser::setPhone);
        userRepository.save(updatedUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void setPassword(NewPasswordDto newPasswordDto, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
        String oldPassword = newPasswordDto.getCurrentPassword();
        String newPassword = newPasswordDto.getNewPassword();
        manager.changePassword(oldPassword, newPassword, user);
    }

    @Override
    public void updateUserImage(MultipartFile image, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
        Image usersImage = user.getImage();
        Image newImage = imageService.uploadImage(image);
        user.setImage(newImage);
        user.setImageUrl("/image/" + newImage.getId());
        userRepository.save(user);
        if (user.getImage() != null) {
            imageService.removeImage(usersImage);
        }
    }
   /*  if (user.getImage() != null) {
        imageService.removeImage(usersImage);
    }
        user.setImage(newImage);
        user.setImageUrl("/image/" + newImage.getId());
        userRepository.save(user);*/

/*    @Override
   public byte[] updateUserImage(MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
        Image usersImage = user.getImage();
        Image newImage = imageService.uploadImage(image);
        byte[] imageBytes = image.getBytes();
        if (user.getImage() != null) {
            imageService.removeImage(usersImage);
        }
        user.setImage(newImage);
        user.setImageUrl("/images/" + newImage.getId());
        userRepository.save(user);
        return imageBytes;
    }*/

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}

