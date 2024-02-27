package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.security.logger.FormLogInfo;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    @Value("${image.user.dir.path}")
    private String avatarsDir;//полный путь к папке... (application.properties -> image.user.dir.path=./src/main/resources/images/avatars)
    @Value("${image.ads.dir.path}")
    private String adsDir;//полный путь к папке ads... (application.properties)

    private final ImageRepository imageRepository;
    private final UserService userService;

    @Override
    public ImageEntity uploadImage(MultipartFile imageFile) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setData(imageFile.getBytes());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        return imageRepository.save(image);
    }

    @Override
    public ImageEntity getImageById(long id) {
        return null;
    }

    @Override
    public void remove(ImageEntity image) {

    }

    //Далее до конца
    // методы для загрузки аватарок для пользователя
    @Override
    public void updateUserImage(MultipartFile avatarFile, Authentication authentication) throws IOException {
        log.info("STEP_3_updateUserImage" + FormLogInfo.getInfo());

        int getId = userService.getUser(authentication).getId();//имя файла
        String fileName = getId + "." + getExtensions(Objects.requireNonNull(avatarFile.getOriginalFilename()));
        Path filePath = Path.of(avatarsDir, fileName);

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        log.info("STEP_A_createDirectories:   " + FormLogInfo.getInfo());

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);

                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
            log.info("STEP_B_transferTo:   " + FormLogInfo.getInfo());
        }
        ImageEntity avatar = findUserAvatar((long) getId);

        avatar.setFilePath(fileName);//1.jpg
//        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
//        avatar.setData(generateImagePreview(filePath));//если надо превью

        avatar.setUser(userService.findById(getId));

        imageRepository.save(avatar);
        log.info("STEP_C_SAVE___" + FormLogInfo.getInfo());
        //заполнить поле image у UserEntity
        UserEntity userEntity = userService.findById(getId);
        userEntity.setImage(avatar);
        log.info("STEP_D_SAVE_userEntity.setImage(avatar)__" + FormLogInfo.getInfo());
        //надо ли тут запустить мапперы?
    }

    @Override
    public ImageEntity findUserAvatar(String filePath) {
        log.info("FIND_USER_AVATAR" + FormLogInfo.getInfo());
        return imageRepository.findByFilePath(filePath).orElseThrow();
    }

    @Override
    public byte[] getImage(String imagePath) {

        return new byte[0];
    }

    public byte[] generateImagePreview(Path filePath) throws IOException {
        //метод уменьшает размер исходной картинки в малый размер - делает превью
        //для сохранения уменьшенной копии в самой БД
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtensions(filePath.getFileName().toString()), baos);//getExtension
            return baos.toByteArray();
        }
    }

    public String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public ImageEntity findUserAvatar(Long id) {
        return imageRepository.findByUserId(id).orElse(new ImageEntity());
    }
}
