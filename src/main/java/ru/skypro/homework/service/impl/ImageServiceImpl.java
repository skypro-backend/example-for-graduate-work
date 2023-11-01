package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${ad.img.dir.path}")
    private String imgPath;

    private ImageRepository imageRepository;

    private AdRepository adRepository;

    public ImageServiceImpl(ImageRepository imageRepository, AdRepository adRepository) {
        this.imageRepository = imageRepository;
        this.adRepository = adRepository;
    }


    @Override
    public void uploadImg(AdDTO adDTO, MultipartFile img) throws IOException{

        Path filePath = Path.of(imgPath, adDTO.getId() + "." + getExtension(img.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = img.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            bis.transferTo(bos);
            bis.transferTo(baos);


            Image image = adDTO.getImage();
            if (image == null) {
                image = new Image();
            }
            image.setData(baos.toByteArray());
            image.setMediaType(img.getContentType());
            image.setFileSize(img.getSize());
            image.setFilePath(filePath.toString());
            imageRepository.save(image);

            adDTO.setImage(image);

            adRepository.save(adDTO.toAd());


        } catch (Exception e) {
            e.getMessage();
        }
    }

    private Image findImageByAdId(Integer id) {

        Optional<Image> optionalImage = imageRepository.findById(id);

        if (!optionalImage.isEmpty()) {
            return null;
        } else {
            return null;
        }

    }


    private String getExtension(String originalFilename) {

        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

    }

}
