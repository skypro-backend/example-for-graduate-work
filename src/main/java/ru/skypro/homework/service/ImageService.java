package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.pojo.Image;

import java.io.IOException;

public interface ImageService {

    String saveFile(byte[] data, String fileName) throws IOException;

    Image uploadImage(MultipartFile file) throws IOException;

}
