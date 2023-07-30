package ru.skypro.homework.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.repository.AdsRepository;
import ru.skypro.homework.service.repository.ImageRepository;
import ru.skypro.homework.service.repository.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${path.to.ad.images}/") private String pathToAdImages;

    @Value("${path.to.user.images}/") private String pathToUserImages;

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final AdsRepository adRepository;

    public ImageServiceImpl(
            ImageRepository imageRepository, UserRepository userRepository, AdsRepository adRepository)
    {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }
    /**
     * Обновление аватара пользователя
     */
    @Override
    public void updateUserImage(String userName, MultipartFile file) {
        init();
        User user = userRepository.findUserByUserName(userName);
        String imageName = generateRandomFileName(file);
        if (user.getImage() == null) {
            Image image = new Image(imageName);
            imageRepository.save(image);
            user.setImage(image);
            userRepository.save(user);
        } else {
            Image image = user.getImage();
            deleteAvatarIfExists(image.getImageName());
            image.setImageName(imageName);
            imageRepository.save(image);
        }
        File tempFile = new File(Path.of(pathToUserImages).toAbsolutePath().toFile(), imageName);
        writeFile(tempFile, file);
    }
    /**
     * Обновление изображения
     */
    @Override
    public void updateAdImage(Integer id, MultipartFile file) {
        init();
        Ad ad = adRepository.findById(id).orElseThrow();
        String imageName = generateRandomFileName(file);
        Image image = new Image(imageName);
        ad.setImage(image);
        imageRepository.save(image);
        File tempFile = new File(Path.of(pathToAdImages).toAbsolutePath().toString(), imageName);
        adRepository.save(ad);
        writeFile(tempFile, file);
    }
    /**
     * Удаление аватара
     */
    private void deleteAvatarIfExists(String fileName) {
        Path path = Paths.get(Path.of(pathToUserImages).toAbsolutePath().toString(), fileName);

        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("File is successfully deleted.");
            } else {
                System.out.println("File deletion failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(File tempFile, MultipartFile file) {
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    private String generateRandomFileName(MultipartFile file) {
        String imageName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        while (imageRepository.findAdImageByImageName(imageName) != null) {
            imageName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        }
        return imageName;
    }
    /**
     * Получение изображения
     */
    @Override
    public FileSystemResource getUserImage(Integer id) throws IOException {
        User user = userRepository.findById(id).orElseThrow();
        return new FileSystemResource(Path.of(pathToUserImages, user.getImage().getImageName()));
    }


    @Override
    public FileSystemResource getAdImage(Integer id) throws IOException {
        Ad ad = adRepository.findById(id).orElseThrow();
        Image image = ad.getImage();
        return new FileSystemResource(Path.of(pathToAdImages, image.getImageName()));
    }

    private void init() {
        File adImagesDir = new File(pathToAdImages);
        File userImagesDir = new File(pathToUserImages);
        if (!adImagesDir.exists()) {
            adImagesDir.mkdirs();
        }
        if (!userImagesDir.exists()) {
            userImagesDir.mkdirs();
        }
    }
}
