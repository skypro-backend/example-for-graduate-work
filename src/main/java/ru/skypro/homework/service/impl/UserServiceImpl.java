package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exceptions.EmptyException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final ImageService imageService;

    private final Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getAuthorizedUser() {
        logger.info("User getAuthorizedUser is running");
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principalUser = (UserDetails) currentUser.getPrincipal();
        return userRepo.findByEmail(principalUser.getUsername())
                .orElseThrow(() -> new EmptyException("Пользователь не найден"));
    }

    public void setPassword(final String email, final String password) {
        logger.info("User setPassword is running");
        String encodedPassword = encoder.encode(password);
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new EmptyException("Пользователь не найден"));
        user.setPassword(encodedPassword);
        userRepo.save(user);

    }

    @Override
    public UpdateUserDTO updateMyProfile(final UpdateUserDTO updateUserDTO) {
        logger.info("User updateMyProfile is running");
        User user = this.getAuthorizedUser();
        userRepo
                .findById(Math.toIntExact(user.getId()))
                .map(oldUser -> {
                    oldUser.setFirstName(updateUserDTO.getFirstName());
                    oldUser.setLastName(updateUserDTO.getLastName());
                    oldUser.setPhone(updateUserDTO.getPhone());
                    return userMapper.convertToUpdateUserDTO(userRepo.save(oldUser));
                });
        return updateUserDTO;
    }

    @Override
    public UserDto findById(Long id) {
        logger.info("User findById is running");
        if (id == null) {
            throw new EmptyException("Пользователь не найден");
        }
        return userRepo.findById(id);
    }

    @Override
    public byte[] updateMyImage(final MultipartFile imageFile) throws IOException {
        logger.info("User updateMyImage is running");
        User user = this.getAuthorizedUser();
        Image image;

        if (imageService.checkUserImage(Math.toIntExact(user.getId()))) {
            image = imageService.updateImage(imageFile, Math.toIntExact(user.getId()));
        } else {
            image = imageService.saveImageToUser(imageFile);
            user.setImage(image);
            userRepo.save(user);
            imageService.saveImageToDb(image);
        }
        return imageService.getImage(image.getId());
    }

    private boolean checkPassword(final String email, final String password) {
        logger.info("User checkPassword is running");
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new EmptyException("Пользователь не найден"));
        return encoder.matches(password, user.getPassword());
    }

    @Override
    public boolean setNewPassword(final String email, final String currentPassword, final String newPassword) {
        logger.info("User setNewPassword is running");
        if (checkPassword(email, currentPassword)) {
            setPassword(email, newPassword);
            return true;
        }
        return false;
    }

}
