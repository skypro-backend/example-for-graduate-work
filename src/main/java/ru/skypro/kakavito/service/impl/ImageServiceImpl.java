package ru.skypro.kakavito.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.exceptions.ImageNotFoundException;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.mappers.UserMapper;
import ru.skypro.kakavito.model.Image;
import ru.skypro.kakavito.model.User;
import ru.skypro.kakavito.repository.ImageRepo;
import ru.skypro.kakavito.repository.UserRepo;
import ru.skypro.kakavito.service.ImageService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepo imageRepo;
    private final UserRepo userRepo;
    private final UserMapper userMapper;


    private long MAX_SIZE = 1024 * 600;
    @Value("${path.to.images.folder}")
    private String imagesDir;
    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public Image findImageById(int id) {
        return imageRepo.findById(id).orElseThrow(() -> {
            throw new ImageNotFoundException(String.format("Photo with id %s not found", id));
        } );
    }

    @Override
    @Transactional
    public Image upLoadImage(MultipartFile file) throws IOException, ImageSizeExceededException {
        if (file == null) {
            throw new MultipartException("Photo was null");
        }
        if (file.getSize() > MAX_SIZE) {
            throw new ImageSizeExceededException("Photo size is too big, max size ", MAX_SIZE);
        }

        Image newImage = new Image();

        newImage.setMediaType(file.getContentType());
        newImage.setFileSize(file.getSize());
        newImage.setData(generateDataForDB(file));

        imageRepo.save(newImage);

        return newImage;
    }

    private byte[] generateDataForDB(MultipartFile file) throws IOException {
        try (InputStream is = file.getInputStream();
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int read;
            byte[] buffer = new byte[1024];
            while ((read = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, read);
            }
            return baos.toByteArray();
        }
    }

    @Override
    public void deleteImage(int imageId) {
        logger.info("Photo deleted");
        if (imageRepo.findById(imageId).isEmpty()) {
            throw new ImageNotFoundException(String.format("Photo with id %s not found", imageId));
        }
        imageRepo.deleteById(imageId);
    }


    @Override
    public void updateImage(int id, MultipartFile image) {
        logger.info("Photo saved {}", id);

        User user = userRepo.findUserById(Math.toIntExact(id));
        Image newImage = new Image();

        try {
            newImage.setData(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Ошибкааааааааа" + e);
        }
        System.out.println(id);

        imageRepo.save(newImage);
    }


    @Override
    public boolean checkUserImage(int userId) {
        logger.info("ImageService checkUserImage is running");
        return imageRepo.findByUserId(userId).isPresent();
    }

}
