package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constants.ErrorMessages;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.image.ImageStorageService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageStorageService imageStorageService;

    @Override
    public ru.skypro.homework.dto.User getUser() {

        return userMapper.toDto(
                getCurrentUser()
        );
    }

    @Override
    public ru.skypro.homework.dto.User updateUser(
            UpdateUser updateUser
    ) {

        User user = getCurrentUser();

        userMapper.updateUserFromDto(
                updateUser,
                user
        );

        return userMapper.toDto(
                userRepository.save(user)
        );
    }

    @Override
    public void updateImage(
            MultipartFile image
    ) {

        User user = getCurrentUser();

        try {

            user.setImage(
                    imageStorageService.saveUserImage(image)
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    ErrorMessages.IMAGE_SAVE_ERROR
            );
        }

        userRepository.save(user);
    }

    private User getCurrentUser() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                ErrorMessages.USER_NOT_FOUND
                        )
                );
    }

    @Override
    public void updatePassword(
            NewPassword newPassword
    ) {

        User user = getCurrentUser();


        if (!passwordEncoder.matches(
                newPassword.getCurrentPassword(),
                user.getPassword()
        )) {

            throw new IllegalArgumentException(
                    "Wrong current password"
            );
        }


        user.setPassword(
                passwordEncoder.encode(
                        newPassword.getNewPassword()
                )
        );


        userRepository.save(user);
    }
}