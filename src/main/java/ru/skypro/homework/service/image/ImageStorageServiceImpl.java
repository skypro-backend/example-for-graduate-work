package ru.skypro.homework.service.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageStorageServiceImpl implements ImageStorageService {


    @Value("${app.images.dir}")
    private String imagesDir;


    @Override
    public String saveUserImage(MultipartFile image) throws IOException {

        return saveImage(
                image,
                "users"
        );
    }


    @Override
    public String saveAdImage(MultipartFile image) throws IOException {

        return saveImage(
                image,
                "ads"
        );
    }


    private String saveImage(
            MultipartFile image,
            String folder
    ) throws IOException {


        if (image == null || image.isEmpty()) {
            return null;
        }


        Path directory =
                Paths.get(
                        imagesDir,
                        folder
                );


        Files.createDirectories(directory);


        String originalFilename =
                image.getOriginalFilename();


        String extension = "";

        if (originalFilename != null &&
                originalFilename.contains(".")) {

            extension =
                    originalFilename.substring(
                            originalFilename.lastIndexOf(".")
                    );
        }


        String fileName =
                UUID.randomUUID()
                        .toString()
                        + extension;


        Path filePath =
                directory.resolve(fileName);


        Files.write(
                filePath,
                image.getBytes()
        );


        // этот путь будем отдавать фронту
        return "/" + imagesDir + "/" + folder + "/" + fileName;
    }


    @Override
    public byte[] loadImage(
            String fileName
    ) throws IOException {


        Path path =
                Paths.get(
                        fileName
                );


        return Files.readAllBytes(path);
    }
}