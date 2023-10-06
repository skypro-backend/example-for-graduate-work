package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImagesRepository imagesRepository;

    @Value("${path.image.dir}")
    private String pathImageDir;

    @Override
    public byte[] getImageInByte(String fileName) {
        Path path = Path.of(pathImageDir + "/" + fileName);
        byte[] imageByte;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageIO.read(new File(path.toUri()));
            ImageIO.write(bufferedImage, getExtension(fileName), baos);
            imageByte = baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error image, " + e.getMessage());
        }
        return imageByte;
    }

    @Override
    public Image changeUserImage(Long userId, Long imageId, MultipartFile multipartFile) throws IOException {
        String fileName = "user-image-"
                + userId
                + "." + getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Path path = Path.of(pathImageDir, fileName);
        Image image = convertToImage(path, imageId, multipartFile, fileName);
        return imagesRepository.save(image);
    }

    @Override
    public Image changeAdImage(Long adId, Long imageId, MultipartFile multipartFile) throws IOException {
        String fileName = "ad-image-"
                + adId
                + "." + getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Path path = Path.of(pathImageDir, fileName);
        Image image = convertToImage(path, imageId, multipartFile, fileName);
        return imagesRepository.save(image);
    }


    private Image convertToImage(Path path, Long idImage, MultipartFile multipartFile, String fileName) throws IOException {
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        try (InputStream is = multipartFile.getInputStream();
             OutputStream os = Files.newOutputStream(path, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Optional<Image> imageOptional = imagesRepository.findById(idImage);
        Image image = imageOptional.orElseGet(Image::new);
        image.setFileName(fileName);
        image.setMediaType(multipartFile.getContentType());
        return image;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}


