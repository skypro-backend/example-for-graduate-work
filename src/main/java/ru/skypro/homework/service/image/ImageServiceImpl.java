package ru.skypro.homework.service.image;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.custom_exception.CommentNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${path.to.images}")
    private String pathToImages;

    /**
     * Создает картинку и возращает её url
     *
     * @param file картинка
     * @return url картинки
     */
    @Override
    public String createImage(MultipartFile file) {
        checkDirectory();
        String imageName = UUID.randomUUID() + "_" + Instant.now().getEpochSecond() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            Files.write(Path.of(pathToImages + imageName),file.getBytes());
        } catch (IOException ignored) {}
        return "/" + imageName;
    }
    /**
     * Возвращает картинку в виду массива байт
     *
     * @param imageName url картинки
     * @return картинку в виду массива байт
     */
    @Override
    public byte[] getImage(String imageName) {
        byte[] image = null;
        try {
            image = Files.readAllBytes(Path.of(pathToImages + imageName));
        } catch (IOException ignored) {}
        return image;
    }

    /**
     * Проверяет существует ли каталог, в котором хранятся картики, и если нет, создает его
     *
     */
    private void checkDirectory() {
        File imagesDir = new File(pathToImages);
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }
    }
    /**
     * Проверяет существует ли каталог, в котором хранятся картики, и если да, удаляет его
     *
     * @param url картинки
     */
    @Override
    public void deleteImage(String url) throws IOException {
        Files.deleteIfExists(Path.of(pathToImages + url.replaceFirst("/","")));
    }
}