package ru.skypro.homework.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.ImageDTO;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.exceptions.WrongCurrentPasswordException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Objects;

/**
 * The class with methods for updating and getting user's account
 * @author Sulaeva Marina
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageRepository imageRepository;

    /**
     * The method for updating password
     */
    @Override
    public void updatePassword(NewPassword newPassword, String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        if (passwordEncoder.matches(newPassword.getCurrentPassword(), users.getPassword())) {
            users.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            usersRepository.save(users);
        } else {
            throw new WrongCurrentPasswordException();
        }
    }
    /**
     * The method for getting information about user's account
     */
    @Override
    public User getInformation(String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return User.toUser(users);
    }

    /**
     * The method for updating information (first name, last name and phone) for user's account
     */
    @Override
    public UpdateUser updateInformationAboutUser(UpdateUser updateUser, String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        users.setFirstName(updateUser.getFirstName());
        users.setLastName(updateUser.getLastName());
        users.setPhone(updateUser.getPhone());
        usersRepository.save(users);
        return UpdateUser.toUpdateUser(users);
    }

    /**
     * The method for updating image for user's account
     */
    @Override
    public ImageDTO updateImage(MultipartFile file, String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Image image;
        if (!Objects.isNull(users.getImage())) {
            image = imageRepository.findById(users.getImage().getId()).orElse(new Image());
        } else {
            image = new Image();
            image.setId(users.getId().toString());
        }
        try {
            byte[] imageBytes = file.getBytes();
            image.setImage(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        Image returnImage = imageRepository.save(image);
        users.setImage(image);
        usersRepository.save(users);
        return ImageDTO.fromImage(returnImage);
    }
    /**
     * The method for getting image for user's account
     */
    @Override
    public byte[] getImage(String id) {
        Image image = imageRepository.findById(id).orElseThrow();
        return image.getImage();
    }



}