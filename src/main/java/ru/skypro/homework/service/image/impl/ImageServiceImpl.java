package ru.skypro.homework.service.image.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.service.image.ImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${image.download.path.goods}")
    private String pathToImagesOfGoods;

    @Value("${image.download.path.avatars}")
    private String pathToImagesOfAvatars;

    private final CommentMapper commentMapper;

    public ImageServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public String consumeImageOfGoods(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        assert originalFilename != null;
        String[] splittedOriginalFileName = originalFilename.split("\\.");
        String filename = splittedOriginalFileName[0] + commentMapper.mapDateToLong(LocalDateTime.now());
        String fullFilePath = pathToImagesOfGoods + filename + "." + splittedOriginalFileName[1];
        image.transferTo(new File(fullFilePath));
        return fullFilePath.split("static")[1];
    }

    @Override
    public void deleteImageOfGoods(String urlToImage) throws IOException {
        Path filePath = getFullPathToImageOfGoods(urlToImage);
        Files.delete(filePath);
    }

    @Override
    public String consumeImageOfAvatar(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        assert originalFilename != null;
        String[] splittedOriginalFileName = originalFilename.split("\\.");
        String filename = splittedOriginalFileName[0] + commentMapper.mapDateToLong(LocalDateTime.now());
        String fullFilePath = pathToImagesOfAvatars + filename + "." + splittedOriginalFileName[1];
        image.transferTo(new File(fullFilePath));
        return fullFilePath.split("static")[1];
    }

    @Override
    public void deleteImageOfAvatars(String urlToImage) throws IOException {
        Path fullPathToImage = getFullPathToImageOfAvatars(urlToImage);
        Files.delete(fullPathToImage);
    }

    @Override
    public Path getFullPathToImageOfGoods(String urlToImage) {
        String pathToStatic = pathToImagesOfGoods.split("static")[0];
        return Paths.get(pathToStatic + "static" + urlToImage);
    }

    @Override
    public Path getFullPathToImageOfAvatars(String urlToImage) {
        String pathToStatic = pathToImagesOfAvatars.split("static")[0];
        return Paths.get(pathToStatic + "static" + urlToImage);
    }

    @Override
    public byte[] imageToByteArray(Path fullPathToImage) throws IOException {
        return Files.readAllBytes(fullPathToImage);
    }

}
