package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.ModelEntity;
import ru.skypro.homework.model.PhotoEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final PhotoRepository photoRepository;
    private final UserMapper userMapper;

    @Value("${path.to.photos.folder}")
    private String photoDir;


    public ImageServiceImpl(PhotoRepository photoRepository, UserMapper userMapper) {
        this.photoRepository = photoRepository;
        this.userMapper = userMapper;
    }

    ///////////////////////перенести в AdService
//    @Override
//    public PhotoEntity updateAdImage(Integer id, MultipartFile image, Path filePath) throws IOException {
//        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
//        AdEntity adEntity = adRepository.findById(id).orElseThrow(RuntimeException::new);
//        PhotoEntity photo = adMapper.mapMultipartFileToPhoto(image);
//        photoRepository.save(photo);
//        log.info("новое phtotId = {}", photo.getId());
//        adEntity.setPhoto(photo);
//
//        String urlToPhoto = "/photo/image/" + adEntity.getPhoto().getId();
//        adEntity.setImage(urlToPhoto);
//        log.info("URL для перехода фронта к методу возврата photo: {}", urlToPhoto);
//
//        //адрес до директории хранения фото на ПК
//        filePath = Path.of(photoDir, adEntity.getPhoto().getId() +/* "-" + properties.getTitle() + */"."
//                + this.getExtension(image.getOriginalFilename()));
//        log.info("путь к файлу: {}", filePath);
//
//        //сохранение на ПК
//        this.saveFileOnDisk(adEntity.getPhoto(), filePath);
//
//        //сохранение сущности adEntity в БД
//        adRepository.save(adEntity);
//        return photo;
//    }

    @Override
    public ModelEntity updateEntitiesPhoto(MultipartFile image, ModelEntity entity) throws IOException {
        //если у сущности уже есть картинка, то нужно ее удалить
        if (entity.getPhoto() != null) {
            photoRepository.delete(entity.getPhoto());
        }

        //заполняем поля photo и сохраняем фото в БД
        PhotoEntity photoEntity = userMapper.mapMuptipartFileToPhoto(image);
        entity.setPhoto(photoEntity);
        photoRepository.save(photoEntity);

        //записываем URL для перехода фронта к методу возврата аватара
        String urlToAvatar = "/photo/image/" + entity.getPhoto().getId();
        entity.setImage(urlToAvatar);
        log.info("URL для перехода фронта к методу возврата аватара: {}", urlToAvatar);

        //добавляем в сущность URL
        entity.setImage(urlToAvatar);

        //адрес до директории хранения фото на ПК
        Path filePath = Path.of(photoDir, entity.getPhoto().getId() + "."
                + this.getExtension(image.getOriginalFilename()));
        log.info("путь к файлу для хранения фото на ПК: {}", filePath);

        //добавляем в сущность фото путь где оно хранится на ПК
        entity.getPhoto().setFilePath(filePath.toString());

        //добавляем в сущность путь на ПК
        entity.setFilePath(filePath.toString());

        //сохранение на ПК
        this.saveFileOnDisk(image, filePath);

        return entity;
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
///////////////////////////////////////

    public byte[] getPhotoFromDisk(PhotoEntity photo) throws IOException {
        Path path1 = Path.of(photo.getFilePath());
        return Files.readAllBytes(path1);
    }
    @Override
    public String getExtension(String fileName) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
