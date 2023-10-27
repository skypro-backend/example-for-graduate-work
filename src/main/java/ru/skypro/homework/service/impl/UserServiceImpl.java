package ru.skypro.homework.service.impl;


import liquibase.util.FilenameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public void UpdateImage(MultipartFile file, String username) throws IOException {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<String> SUPPORTED_EXTENSIONS = Arrays.asList("png", "jpg", "jpeg");
        String filename = file.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        if (!SUPPORTED_EXTENSIONS.contains(type)) {
            throw new UnsupportedFormatException();
        }
        byte[] image = file.getBytes();
        String pathString = "D:\\"  + users.getFirstName() + users.getLastName() + "." + type;
        Path path = Paths.get(pathString);
        File fileImage = new File(pathString);
        Files.write(path, image);
        users.setImage(fileImage.getPath());
        usersRepository.save(users);
    }

    @Override
    public Resource getImage(String username) throws IOException {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return new ByteArrayResource(Files.readAllBytes(Paths.get(users.getImage())));
    }
}