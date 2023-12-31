package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exceptions.ImageSizeExceededException;
import ru.skypro.homework.model.User;

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