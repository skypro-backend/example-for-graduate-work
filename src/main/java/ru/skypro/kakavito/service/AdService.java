package ru.skypro.kakavito.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.dto.AdsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateAdDTO;
import ru.skypro.kakavito.dto.ExtendedAdDTO;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;

import java.io.IOException;

public interface AdService {
    AdsDTO findAll();
    ExtendedAdDTO findById(Long id);
    AdsDTO getAdByAuthUser();
    AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile imageFile) throws IOException, ImageSizeExceededException;
    AdDTO updateAd(Long id, CreateOrUpdateAdDTO createOrUpdateAdDTO);

    void updateImage(Long id, MultipartFile imageFile);

    void deleteAd(int ad);
}