package ru.skypro.kakavito.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.dto.AdsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateAdDTO;
import ru.skypro.kakavito.dto.ExtendedAdDTO;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;

import java.io.IOException;

public interface AdService {
    AdsDTO findAllAds();
    ExtendedAdDTO findById(int id);
    AdsDTO getAdByAuthUser();
    AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile imageFile) throws IOException, ImageSizeExceededException;
    AdDTO updateAd(int id, CreateOrUpdateAdDTO createOrUpdateAdDTO);
    void updateAdImage(int id, MultipartFile imageFile) throws IOException;
    void deleteAd(int ad);
}