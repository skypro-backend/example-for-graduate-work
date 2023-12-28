package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.MissingImageException;
import ru.skypro.homework.exceptions.PasswordNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.CommentEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.UsersService;
import ru.skypro.homework.service.helper.AuthenticationCheck;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    private final AuthenticationCheck authenticationCheck;
    private final UserMapper userMapper;
    private final UserEntityRepository userEntityRepository;

    /**
     * Checking user's data using {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br> for setting password and saving
     * <br> {@code userEntityRepository.save(userEntity)};
     * @param newPassword
     * @param userDetails
     */
    @Override
    public void setPassword(NewPassword newPassword, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));

            userEntity.setPassword(new BCryptPasswordEncoder().encode(newPassword.getNewPassword()));
            userEntityRepository.save(userEntity);
    }

    /**
     * Getting user's details using {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br> and
     * <br> {@link UserMapper#userEntityToUser(UserEntity)};
     * @param userDetails
     * @return
     */
    @Override
    public User getUser(CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return userMapper.userEntityToUser(userEntity);
    }

    /**
     * Updating user's information using
     * {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br> checking if data is not null, and next updating
     * <br> {@code userEntityRepository.save(userEntity);}
     * @param updateUser
     * @param userDetails
     * @return
     */
    @Override
    public UpdateUser updateUser(UpdateUser updateUser, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));

        if ((Optional.ofNullable(updateUser)).isEmpty()) {
            throw new EntityNotFoundException("There is no information for updating");
        } else if (updateUser.getFirstName() != null) {
            userEntity.setFirstName(updateUser.getFirstName());
        } else if (updateUser.getFirstName() != null) {
            userEntity.setLastName(updateUser.getFirstName());
        } else if (updateUser.getPhone() != null) {
            userEntity.setPhone(updateUser.getPhone());
        }
        userEntityRepository.save(userEntity);
        return updateUser;
    }

    /**
     * Updating user's image using {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br> and checking if image's content type is suitable
     * (<b>jpeg</b>, <b>png</b>, <b>gif</b>);
     * @param image
     * @param userDetails
     */

    @Override
    public void updateUserImage(MultipartFile image, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found "));

        if (!image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/png") && !image.getContentType().equals("image/gif")) {
            throw new MissingImageException("Некорректный формат изображения пользователя!" );
        }
        // туДу заменить методом обработки фото

        //
        userEntity.setImageEntity(null);
    }
}
