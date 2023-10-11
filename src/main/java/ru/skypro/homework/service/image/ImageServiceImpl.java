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
import java.nio.file.Path;
import java.time.Instant;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${path.to.images}/")
    private String pathToImages;

    /**
     * Возвращает ссылку на картинку
     *
     * @param file картинка
     * @return ссылку на картинку
     * @throws MalformedURLException если URL-адрес неправильно сформирован
     * @throws RuntimeException если введен неверный формат данных
     */
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

    /**
     * Проверяет существует ли каталог, в котором хранятся картики, и если нет, создает его
     *
     */
    private void checkDirectory() {
        File imagesDir = new File(pathToImages);
        if (!imagesDir.exists()) imagesDir.mkdirs();
    }
}