package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exceptions.BigImageException;
import ru.skypro.homework.exceptions.EmptyException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepo;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static org.apache.commons.io.FilenameUtils.getExtension;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepo imageRepo;

    @Value("${path.to.images.folder}")
    private String imagesDir;

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public Image saveImageToUser(MultipartFile imageFile) throws IOException {
        logger.info("ImageService saveImageToUser is running");
        long imageSize = imageFile.getSize();
        checkSize(imageSize);

        Image image = new Image();
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        Image savedImage = saveImageToDb(image);

        Path filePath = Path.of(imagesDir, image.getId() + "." +
                getExtension(imageFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        savedImage.setFilePath(filePath.toString());

        try {
            saveImage(imageFile, savedImage);
        } catch (IOException e) {
            imageRepo.delete(savedImage);
        }
        return saveImageToDb(savedImage);
    }

    @Override
    public Image updateImage(MultipartFile imageFile, Long id) throws IOException {
        long imageSize = imageFile.getSize();
        checkSize(imageSize);
        Image image;

        image = imageRepo.findByUserId(Math.toIntExact(id)).orElse(null);

        if (image == null) {
            image = imageRepo.findByAdId(Math.toIntExact(id))
                    .orElseThrow(() -> new EmptyException("Обновляемое изображение не найдено"));
        }

        Path filePath = Path.of(imagesDir, image.getId() + "." +
                getExtension(image.getFilePath()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setFilePath(Path.of(imagesDir, image.getId() + "." +
                getExtension(imageFile.getOriginalFilename())).toString());
        saveImage(imageFile, image);
        return saveImageToDb(image);
    }

    @Override
    public Image saveImageToDb(Image image) {
        return imageRepo.save(image);
    }

    @Override
    public byte[] getImage(int imageId) throws IOException {
        Path path = Path.of(imagesDir, imageId + "." + getExtension(imageRepo.findFilePathById(imageId)));
        return new ByteArrayResource(Files
                .readAllBytes(path)
        ).getByteArray();
    }

    @Override
    public boolean checkUserAvatar(int userId) {
        return imageRepo.findByUserId(userId).isPresent();
    }

    private void checkSize(long imageSize) {
        if (imageSize > (1024 * 5000)) {
            throw new BigImageException("Размер изображения (" + (imageSize /1024 / (double) 1000)
                    + " MB) превышает максимально допустимое значение, равное 5 MB");
        }
    }

    private void saveImage(MultipartFile imageFile, Image image) throws IOException {
        try (
                InputStream is = imageFile.getInputStream();
                OutputStream os = Files.newOutputStream(Path.of(image.getFilePath()), CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
    }
}
