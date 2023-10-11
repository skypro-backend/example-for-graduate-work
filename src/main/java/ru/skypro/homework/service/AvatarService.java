package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.pojo.Avatar;


import java.io.IOException;

public interface AvatarService {

    String saveFile(byte[] data, String fileName) throws IOException;

    Avatar uploadAvatar(MultipartFile file) throws IOException;
}
