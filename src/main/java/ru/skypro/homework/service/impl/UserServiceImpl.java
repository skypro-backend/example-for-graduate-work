package ru.skypro.homework.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
import java.util.UUID;

import static java.nio.file.Files.copy;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageRepository imageRepository;


//    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
//        this.usersRepository = usersRepository;;
//    }

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

    @Override
    public User getInformation(String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return User.toUser(users);
    }

    @Override
    public UpdateUser updateInformationAboutUser(UpdateUser updateUser, String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        users.setFirstName(updateUser.getFirstName());
        users.setLastName(updateUser.getLastName());
        users.setPhone(updateUser.getPhone());
        usersRepository.save(users);
        return UpdateUser.toUpdateUser(users);
    }
    @Override
    public Image updateImage(MultipartFile file, String username) {
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
        return returnImage;
    }

    @Override
    public byte[] getImage(String id) {
        Image image = imageRepository.findById(id).orElseThrow();
        return image.getImage();
    }



}