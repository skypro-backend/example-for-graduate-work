package ru.skypro.homework.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface ImageService {
    String createImage(MultipartFile file) throws MalformedURLException;
}