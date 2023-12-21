package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepo;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final ImageRepo imageRepo;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User findById(Long id) {
        logger.info("User getUserById is running");
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User setPassword(User user) {
        logger.info("User setPassword is running");
        User existing = user;
        existing.setPassword((user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User updateMyProfile(User user) {
        logger.info("User updateUser is running");
        User existing = user;
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setPhone(user.getPhone());
        return userRepo.save(user);
    }

    public Image setImage(Long id, MultipartFile file) throws IOException {
            logger.info("User setImage is running");
            User user = userRepo.findById(id).orElseThrow(() -> new EmptyException("User not found"));
            Path filePath = Path.of("./image", user + "." + getExtension(file.getOriginalFilename()));

            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);

            try (InputStream is = file.getInputStream();
                 OutputStream os = Files.newOutputStream(filePath, StandardOpenOption.CREATE_NEW);
                 BufferedInputStream bis = new BufferedInputStream(is, 1024);
                 BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
                bis.transferTo(bos);
            }
            Image image = imageRepo.findById(id).orElse(new Image());
            image.setUser(user);
            image.setFilePath(filePath.toString());
            image.setFileSize(file.getSize());
            image.setMediaType(file.getContentType());
            image.setData(file.getBytes());
            imageRepo.save(image);
            return image;

    }
}
