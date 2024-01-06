package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.UsersService;
import ru.skypro.homework.service.helper.AuthenticationCheck;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    private final AuthenticationCheck authenticationCheck;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final UserEntityRepository userEntityRepository;
    private final ImageServiceImpl imageService;
    private final ImageEntityRepository imageEntityRepository;

    /**
     * Checking user's data using {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br> next use {@link AuthenticationCheck#accessCheck(CustomUserDetails, UserEntity)},
     * <br> and set new password for user {@code userEntity.setPassword(encoder.encode(newPassword.getNewPassword()));}
     * <br> next save it
     * <br> {@code userEntityRepository.save(userEntity)};
     * @param newPassword NewPassword;
     * @param userDetails CustomUserDetails;
     */
    @Override
    public void setPassword(NewPassword newPassword, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("The USER/ADMIN to change password is not found"));
        authenticationCheck.accessCheck(userDetails, userEntity);
        userEntity.setPassword(encoder.encode(newPassword.getNewPassword()));

        userEntityRepository.save(userEntity);
    }

    /**
     * Getting user's details using {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br> next {@link AuthenticationCheck#accessCheck(CustomUserDetails, UserEntity)},
     * <br> and {@link UserMapper#userEntityToUser(UserEntity)};
     * @param userDetails CustomUserDetails;
     * @return userMapper.userEntityToUser(userEntity);
     */
    @Override
    public User getUser(CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        authenticationCheck.accessCheck(userDetails, userEntity);
        return userMapper.userEntityToUser(userEntity);
    }

    /**
     * Updating user's information using
     * {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br> checking if data is not null (like <b>firstName</b>, <b>lastName</b>, and <b>phone</b>),
     * <br> and next updating
     * <br> {@code userEntityRepository.save(userEntity);}
     * @param updateUser UpdateUser;
     * @param userDetails CustomUserDetails;
     * @return updateUser;
     */
    @Override
    public UpdateUser updateUser(UpdateUser updateUser, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if (!updateUser.getFirstName().isEmpty()) {
            userEntity.setFirstName(updateUser.getFirstName());
        }
        if (!updateUser.getLastName().isEmpty()) {
            userEntity.setLastName(updateUser.getLastName());
        }
        if (!updateUser.getPhone().isEmpty()) {
            userEntity.setPhone(updateUser.getPhone());
        }

        userEntityRepository.save(userEntity);
        return updateUser;
    }

    /**
     * Updating user's image using {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br> and {@link AuthenticationCheck#accessCheck(CustomUserDetails, UserEntity)});
     * <br>
     * <br> Next using {@code imageService.uploadImageToServer(image, userEntity.getId(), userEntity.getUsername())},
     * <br> save new image {@code userEntityRepository.save(userEntity);}
     * <br> and delete previous {@code imageEntityRepository.delete(obsoleteAvatar);}
     * @param image MultipartFile;
     * @param userDetails CustomUserDetails;
     */
    @Override
    public void updateUserImage(MultipartFile image, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found "));

        authenticationCheck.accessCheck(userDetails, userEntity);

        ImageEntity obsoleteAvatar = userEntity.getImageEntity();
        ImageEntity newAvatar = imageService
                .uploadImageToServer(image, userEntity.getId(), userEntity.getUsername());
        userEntity.setImageEntity(newAvatar);
        userEntityRepository.save(userEntity);
        imageEntityRepository.delete(obsoleteAvatar);
    }
}
