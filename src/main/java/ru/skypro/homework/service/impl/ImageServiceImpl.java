package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.AvatarEntity;
import ru.skypro.homework.model.PhotoEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final AvatarRepository avatarRepository;
    private final PhotoRepository photoRepository;
    private final AdRepository adRepository;
    private final AdMapper adMapper;

    @Value("${path.to.photos.folder}")
    private String photoDir;


    public ImageServiceImpl(AvatarRepository avatarRepository, PhotoRepository photoRepository, AdRepository adRepository, AdMapper adMapper) {
        this.avatarRepository = avatarRepository;
        this.photoRepository = photoRepository;
        this.adRepository = adRepository;
        this.adMapper = adMapper;
    }
    @Override
    public PhotoEntity updateAdImage(Integer id, MultipartFile image, Path filePath) throws IOException{
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        AdEntity adEntity = adRepository.findById(id).orElseThrow(RuntimeException::new);
        PhotoEntity photo = adMapper.mapMultipartFileToPhoto(image);
        log.info("новое phtotId = {}", photo.getId());
        adEntity.setPhoto(photo);

        String urlToPhoto = "/photo/image/" + adEntity.getPhoto().getId();
        adEntity.setImage(urlToPhoto);
        log.info("URL для перехода фронта к методу возврата photo: {}", urlToPhoto);

        //адрес до директории хранения фото на ПК
        filePath = Path.of(photoDir, adEntity.getPhoto().getId() +/* "-" + properties.getTitle() + */"."
                + this.getExtension(image.getOriginalFilename()));
        log.info("путь к файлу: {}", filePath);

        //сохранение на ПК
        this.saveFileOnDisk(adEntity.getPhoto(), filePath);

        //сохранение сущности adEntity в БД
        adRepository.save(adEntity);
        return photo;
    }


    @Override
    public void updateUserImage(UserEntity user, MultipartFile image, Path filePath) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        AvatarEntity avatar = avatarRepository.findByUser(user).orElseGet(AvatarEntity::new);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(image.getSize());
        avatar.setMediaType(image.getContentType());
        avatarRepository.save(avatar);
    }

    @Override
    public boolean saveFileOnDisk(MultipartFile image, Path filePath) throws IOException {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        return true;
    }

    public PhotoEntity saveFileOnDisk(PhotoEntity photo, Path filePath) throws IOException {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = new ByteArrayInputStream(photo.getData());
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        return photo;
    }

    public AvatarEntity mapMuptipartFileToAvatar(MultipartFile image) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        AvatarEntity avatar = new AvatarEntity();
        try {
            avatar.setData(image.getBytes());
            avatar.setMediaType(image.getContentType());
            avatar.setFileSize(image.getSize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        avatarRepository.save(avatar);
        return avatar;
    }
    @Override
    public String getExtension(String fileName) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
