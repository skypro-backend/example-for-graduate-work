package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"image.folder.path=${java.io.tmpdir}/image-service-test" // ← ключевое исправление!
})
class ImageServiceImplIntegrationTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private Environment environment;

    @TempDir
    Path tempDir;

    private byte[] testImageBytes = "FAKE_JPEG_DATA".getBytes();
    private byte[] defaultAdImageBytes = "DEFAULT_AD_IMAGE".getBytes();
    private byte[] defaultUserImageBytes = "DEFAULT_USER_IMAGE".getBytes();

    @BeforeEach
    void setUp() throws IOException {
        String imagesDirPath = environment.getProperty("image.folder.path");
        assertThat(imagesDirPath).isNotNull();

        Path imagesDir = Path.of(imagesDirPath);
        if (Files.exists(imagesDir)) {
            Files.walk(imagesDir).sorted((a, b) -> b.compareTo(a)).map(Path::toFile).forEach(File -> File.delete());
        }
        Files.createDirectories(imagesDir);

        Files.write(imagesDir.resolve("ad_default.jpg"), defaultAdImageBytes);
        Files.write(imagesDir.resolve("user_default.jpg"), defaultUserImageBytes);
    }

    @Test
    void saveAdImage_and_getAdImage_shouldSaveAndGetImage() throws IOException {
        imageService.saveAdImage(1, testImageBytes);
        byte[] result = imageService.getAdImage(1);
        assertThat(result).isEqualTo(testImageBytes);
    }

    @Test
    void saveUserImage_and_getUserImage_shouldSaveAndGetImage() throws IOException {
        imageService.saveUserImage(1, testImageBytes);
        byte[] result = imageService.getUserImage(1);
        assertThat(result).isEqualTo(testImageBytes);
    }

    @Test
    void getAdImage_whenAdImageNotFound_shouldReturnDefault() throws IOException {
        byte[] result = imageService.getAdImage(999);
        assertThat(result).isEqualTo(defaultAdImageBytes);
    }

    @Test
    void getUserImage_whenUserImageNotFound_shouldReturnDefault() throws IOException {
        byte[] result = imageService.getUserImage(999);
        assertThat(result).isEqualTo(defaultUserImageBytes);
    }

    @Test
    void getAdImage_whenNoImageAndNoDefault_shouldThrowException() throws IOException {
        String imagesDirPath = environment.getProperty("image.folder.path");
        Path defaultImagePath = Path.of(imagesDirPath, "ad_default.jpg");
        Files.delete(defaultImagePath);

        assertThatThrownBy(() -> imageService.getAdImage(999)).isInstanceOf(EntityNotFoundException.class).hasMessage("Изображение объявления не найдено");
    }

    @Test
    void getUserImage_whenNoImageAndNoDefault_shouldThrowException() throws IOException {
        String imagesDirPath = environment.getProperty("image.folder.path");
        Path defaultImagePath = Path.of(imagesDirPath, "user_default.jpg");
        Files.delete(defaultImagePath);

        assertThatThrownBy(() -> imageService.getUserImage(999)).isInstanceOf(EntityNotFoundException.class).hasMessage("Аватар пользователя не найден");
    }
}