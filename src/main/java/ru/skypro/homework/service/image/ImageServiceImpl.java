package ru.skypro.homework.service.image;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${path.to.images}/")
    private String pathToImages;
    @SneakyThrows
    @Override
    public String createImage(MultipartFile file) throws MalformedURLException {
        checkDirectory();
        String imageName = UUID.randomUUID() + "_" + Instant.now().getEpochSecond() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        File tempFile = new File(Path.of(pathToImages).toAbsolutePath().toFile(), imageName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "/images/" + imageName;
    }
    private void checkDirectory() {
        File imagesDir = new File(pathToImages);
        if (!imagesDir.exists()) imagesDir.mkdirs();
    }
}