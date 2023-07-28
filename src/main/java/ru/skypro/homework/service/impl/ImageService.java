package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
public class ImageService {
    @Value("${path.to.user.images}") //src/main/resources/users/
    private String userImagePath;

    private final UserService userService;

@Lazy
    public ImageService(UserService userService) {
        this.userService = userService;
    }

    public String uploadUserImage(MultipartFile image) throws IOException, UserNotFoundException {
        User user = userService.getUser();
        Path imagePath = Path.of(userImagePath + user.getUsername(),
                "user_image.jpg");
        Files.createDirectories(imagePath.getParent());
        Files.deleteIfExists(imagePath);

        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(imagePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(os);
        ) {
            bis.transferTo(bos);
        }
        return "/" + imagePath.getParent().toString();
    }

    public String uploadAdImage(String title, MultipartFile image) throws IOException, UserNotFoundException {
        User user = userService.getUser();
        Path imagePath = Path.of(userImagePath + user.getUsername() + "/ads",
                image.getOriginalFilename());
        Files.createDirectories(imagePath.getParent());
        Files.deleteIfExists(imagePath);

        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(imagePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(os);
        ) {
            bis.transferTo(bos);
        }
        return "/" + imagePath.toString()
                .replace("\\", "/");
    }
}

