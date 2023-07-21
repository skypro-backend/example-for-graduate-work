package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class ImageService {

    @Value("${path.to.ad.images}/")
    private String pathToAdImages;

    @Value("${path.to.user.images}/")
    private String pathToUserImages;

    private final UserService userService;
    private final UserRepository userRepository;


    public ImageService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public String updateUserImage(MultipartFile file) {
        init();
        User user = userService.getUser();
        String imageAddress = "/users/photo/" + user.getUserId();
        File tempFile = new File(
                Path.of(pathToUserImages).toAbsolutePath().toFile(),
                user.getUserId() + "_user_image.jpg");
        writeFile(tempFile, file);
        return imageAddress;
    }


    private void writeFile(File tempFile, MultipartFile file) {
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!");
        } catch (
                IOException e) {
            throw new RuntimeException();
        }
    }


    private void init() {
        File adImagesDir = new File(pathToAdImages);
        File userImagesDir = new File(pathToUserImages);
        if (!adImagesDir.exists()) {
            adImagesDir.mkdirs();
        }
        if (!userImagesDir.exists()) {
            userImagesDir.mkdirs();
        }
    }

    public String updateAdImage(Integer pk, MultipartFile file) {
        return null;
    }
}

