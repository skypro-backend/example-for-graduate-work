package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.model.Image;
import javax.transaction.Transactional;
import java.io.IOException;

/**
 * Реализация сервиса по работе с изображениями
 */
@Log
@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
      private final ImageRepository imageRepository;

      @Override
      public Image uploadImage (MultipartFile multipartFile) throws IOException {
            log.info ("Было создано изображение");
            Image image = new Image();
            image.setData(multipartFile.getBytes());
            image.setFileSize(multipartFile.getSize());
            image.setMediaType(multipartFile.getContentType());
            log.info ("Изображение было сохранено");
            image.setData(multipartFile.getBytes());
            return imageRepository.save (image);
      }
      @Override
      public byte[] getImage(Integer imageId) {
            Image image = imageRepository.findById(imageId).orElseThrow();
            log.info ("Изображение было получено");
            return image.getData();
      }
}
