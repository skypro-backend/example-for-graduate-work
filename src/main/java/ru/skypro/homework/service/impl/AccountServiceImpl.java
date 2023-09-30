package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.account.NewPassword;
import ru.skypro.homework.dto.account.User;
import ru.skypro.homework.service.AccountService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public boolean updatePassword(NewPassword newPassword) {
        return false;
    }

    @Override
    public User getInfoAboutUser() {
        return null;
    }

    @Override
    public User patchInfoAboutUser(User user) {
        return null;
    }

    @Override
    public boolean updateUserAvatar(MultipartFile image) throws IOException {
        return false;
    }
    static void uploadImage(MultipartFile image, Path filePath) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
    }
}
