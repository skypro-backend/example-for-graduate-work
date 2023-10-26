package ru.skypro.homework.service.impl;


import liquibase.util.FilenameUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.UnsupportedFormatException;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.exceptions.WrongCurrentPasswordException;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UserService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Files.copy;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updatePassword(NewPassword newPassword, String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        String currentPassword = passwordEncoder.encode(newPassword.getCurrentPassword());
        if (currentPassword.equals(users.getPassword())) {
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
    public void UpdateImage(MultipartFile file, String username) throws IOException {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<String> SUPPORTED_EXTENSIONS = Arrays.asList("png", "jpg", "jpeg", "PNG", "JPG", "JPEG","Png", "Jpg", "Jpeg");
//        String ext = file.getContentType();
//        String [] ext = file.getName().split("\\.");
//        if (!SUPPORTED_EXTENSIONS.contains(ext)) {
//            throw new UnsupportedFormatException();
//        }
        byte[] image = file.getBytes();
        String pathString = "D:\\userImage\\"  + username + "." + "PNG";
//        String pathString = "D:\\userImage\\"  + username + "." + ext[1];
        Path path = Paths.get(pathString);
        File fileImage = new File(pathString);
        Files.write(path, image);
        users.setImage(fileImage.getPath());
        usersRepository.save(users);
    }
}