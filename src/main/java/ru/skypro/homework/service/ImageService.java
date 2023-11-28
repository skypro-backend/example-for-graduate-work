package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;

import java.io.IOException;

public interface ImageService {
    ResponseEntity<byte[]> getImage(Long id) throws IOException;

    AdDTO addPhotoAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image);
}
