package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.entity.Images;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

    @Override
    public Images findImage(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void removeImage(Integer id) {
        repository.deleteById(id);
    }

    public Images addImage(MultipartFile file) throws IOException {
        Images img = new Images();
        img.setFilePath(file.getResource().getFilename());
        img.setFileSize((int) file.getSize());
        img.setMediaType(file.getContentType());
        img.setData(file.getBytes());

        return repository.save(img);
    }
}
