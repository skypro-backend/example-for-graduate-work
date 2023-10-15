//package ru.skypro.homework.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import ru.skypro.homework.repository.ImageRepository;
//import ru.skypro.homework.service.ImageService;
//import ru.skypro.homework.model.Image;
//
//import javax.transaction.Transactional;
//import java.awt.*;
//import java.io.IOException;
//
//@Transactional
//@Service
//@RequiredArgsConstructor
//public class ImageServiceImpl implements ImageService {
//      private final ImageRepository imageRepository;
//
//      public Image upload(MultipartFile multipartFile) throws IOException {
//            Image image = new Image ();
//            image.setData(multipartFile.getBytes());
//            image.setFileSize(multipartFile.getSize());
//            image.setMediaType(multipartFile.getContentType());
//            image.setData(multipartFile.getBytes());
//            return imageRepository.save (image);
//      }
//
//}
