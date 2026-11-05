package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public String saveImage(MultipartFile file) {
        if(file == null || file.isEmpty()){
            return null;
        }
        try{
            Path dirPath = Paths.get(uploadDir);
            if(!Files.exists(dirPath)){
                Files.createDirectories(dirPath);
            }
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = dirPath.resolve(fileName);

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, filePath);
            }

            return "/images/" + fileName;
        } catch (IOException ex){
            throw new RuntimeException("Ошибка при сохранении файла", ex);
        }
    }

    @Override
    public byte[] getImage(String fileName) {
        try{
            Path filePath = Paths.get(uploadDir).resolve(fileName);
            if(!Files.exists(filePath)){
                throw new NotFoundException("Картинка не найдена");
            }
            return Files.readAllBytes(filePath);
        }catch (IOException ex){
            throw new NotFoundException("Ошибка при чтении файла");
        }
    }
}
