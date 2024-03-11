package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.exception.WrongPasswordLengthException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

/**
 * Service for setting password, update, receipt of a user
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final PasswordEncoder encoder;

    /**
     * Sets new password
     * @param newPassword is NewPassword DTO consisting of current password and new password
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     */
    @Override
    public void setPassword(NewPassword newPassword, UserDetails userDetails) {
        if (newPassword.getNewPassword().length() < 8 || newPassword.getNewPassword().length() > 16){
            throw new WrongPasswordLengthException("The length of password must be between 8 and 16 symbols");
        }
        if (!encoder.matches(newPassword.getCurrentPassword(),userDetails.getPassword())){
            throw new ForbiddenException("Wrong password!");
        }
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        userEntity.setPassword(new BCryptPasswordEncoder().encode(newPassword.getNewPassword()));
        userRepository.save(userEntity);
    }

    /**
     * Gets info about a user
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return User DTO consisting of user id, email, firstName, lastName, phone, role and image
     */
    @Override
    public User getUser(UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        return userMapper.userToUserDTO(userEntity);
    }

    /**
     * Updates info about a user
     * @param updateUser is UpdateUser DTO consisting of firstname, lastname and phone
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return UpdateUser DTO consisting of firstname, lastname and phone
     */
    @Override
    public UpdateUser updateUser(UpdateUser updateUser, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return updateUser;
    }

    /**
     * Updates an image of a user
     * @param image image of a user
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     */
    @Override
    public void updateImage(MultipartFile image, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        ImageEntity imageEntity = imageService.uploadImage(image);
        imageService.deleteImage(userEntity);
        userEntity.setImageEntity(imageEntity);

        userRepository.save(userEntity);
    }
}
