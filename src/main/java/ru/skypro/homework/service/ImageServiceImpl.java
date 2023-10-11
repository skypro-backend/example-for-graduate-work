package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.pojo.Image;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;


    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;

    }


    @Override
    public String saveFile(byte[] data, String fileName) throws IOException {
        String UPLOAD_DIRECTORY = "C:/Media";
        String filePath = UPLOAD_DIRECTORY + "/" + fileName;
        Path path = Paths.get(filePath);
        Files.write(path, data);
        return filePath;
    }

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] data = file.getBytes();
            String fileName = file.getOriginalFilename();

            // Сохранение файла на локальный диск
            String filePath = saveFile(data, fileName);

            // Сохранение пути к файлу в базе данных
            Image image = new Image();
            image.setImagePath(filePath);
            image.setImageSize(file.getSize());
            image.setImageType(file.getContentType());
            image.setData(file.getBytes());


            imageRepository.save(image);
        } else {
            throw new IllegalArgumentException("Uploaded file is empty");

        }
        return null;
    }

}
