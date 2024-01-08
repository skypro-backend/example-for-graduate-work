package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exceptions.MissingImageException;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
    @Value("${path.to.images.folder}")
    private final String imagePath;
    private final ImageEntityRepository imageEntityRepository;

    public ImageServiceImpl(@Value("${path.to.images.folder}") String imagePath, ImageEntityRepository imageEntityRepository) {
        this.imagePath = imagePath;
        this.imageEntityRepository = imageEntityRepository;
    }

    /**
     * Trying to get image by ID.
     * <br> Firstly, try to find in repository using
     * <br> {@code imageEntityRepository.findById(id)},
     * <br> and next using image file path:
     * <br> {@link Path#of(String, String...)};
     * <br> Also try to catch IOException {@link MissingImageException#MissingImageException(String)}:
     * <br> "The image has not been uploaded to frontEnd"
     * @param id Integer;
     */
    @Transactional
    @Override
    public byte[] getImage(Integer id) {
        ImageEntity image = imageEntityRepository.findById(id)
                .orElseThrow(() -> new MissingImageException("The image provided by id was not found "));
        Path imageFilePath = Path.of(imagePath + image.getFilePath());
        try {
            return Files.readAllBytes(imageFilePath);
        } catch (IOException e) {
            throw new MissingImageException("The image has not been uploaded to frontEnd");
        }
    }

    /**
     * Trying to upload image to server.
     * <br> Firstly, checking, if image's content type is suitable:
     * <br> <b>JPEG</b>, <b>PNG</b>, <b>GIF</b>.
     * <br>
     * <br> Next using ad image path: {@link Path#of(String, String...)};
     * <br> And also try to catch IOException {@link MissingImageException#MissingImageException(String)}:
     * <br> "The image has not been uploaded to server".
     * @param adImage MultipartFile;
     * @param firstPartOfImageName Integer;
     * @param secondPartOfImageName String;
     * @return ImageEntity.builder()
     * .filePath(firstPartOfImageName + "_" + secondPartOfImageName + "." + adImage.getContentType().split("/")[1])
     * .mediaType(adImage.getContentType())
     * .fileSize(adImage.getSize())
     * .build();
     */

    @Override
    public ImageEntity uploadImageToServer(MultipartFile adImage, Integer firstPartOfImageName, String secondPartOfImageName) {

        if (!adImage.getContentType().equals("image/jpeg") && !adImage.getContentType().equals("image/png")
                && !adImage.getContentType().equals("image/gif")) {
            throw new MissingImageException("Incorrect format of the new image!");
        }
        logger.info(adImage.getName());

        Path adImagePath = Path.of(imagePath + firstPartOfImageName + "_" + secondPartOfImageName + "." + adImage.getContentType().split("/")[1]);
        try (InputStream adImageInputStream = adImage.getInputStream()) {
            Files.copy(adImageInputStream, adImagePath, StandardCopyOption.REPLACE_EXISTING);
        }  catch (IOException e) {
            throw new MissingImageException("The image has not been uploaded to server");
        }

        return ImageEntity.builder()
                .filePath(firstPartOfImageName + "_" + secondPartOfImageName + "." + adImage.getContentType().split("/")[1])
                .mediaType(adImage.getContentType())
                .fileSize(adImage.getSize())
                .build();

    }

}
