package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;


import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ImageService imageService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, ImageService imageService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.imageService = imageService;
    }

    @Override
    public void updatePassword(String username, NewPasswordDto newPasswordDto) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(NoSuchElementException::new);

        String encryptedPassword = user.getPassword();
        if (encoder.matches(newPasswordDto.getCurrentPassword(), encryptedPassword)) {
            user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));

            userRepository.save(user);
        } else {
            throw new NoSuchElementException("User inputs wrong current password");
        }
    }

    @Override
    public UserDto getUserInformation(String email) {
        return UserDto.fromUser(userRepository.findUserByUsername(email).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public UserDto updateUser(String email, UpdateUserDto updateUser) {
        User user = userRepository.findUserByUsername(email)
                .orElseThrow(NoSuchElementException::new);
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
        return UserDto.fromUser(user);
    }

    @Override
    public void updateUserAvatar(String username, MultipartFile image) {
        String avatar = imageService.uploadImage(image);
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(NoSuchElementException::new);
        user.setImage(avatar);
        userRepository.save(user);
    }
}
