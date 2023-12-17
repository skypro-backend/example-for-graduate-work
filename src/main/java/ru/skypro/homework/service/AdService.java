package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

public interface AdService {

    AdsDTO getAllAds();

    AdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image);

    ExtendedAdDTO getFullAd(Integer id);

    boolean removeAd(Integer id);

    AdDTO updateAd(Integer id, CreateOrUpdateAdDTO createOrUpdateAdDTO);

    AdsDTO getAllUserAd();

    void updateImageAd(Integer id, MultipartFile image);

    boolean checkAccess(Integer id);
}
