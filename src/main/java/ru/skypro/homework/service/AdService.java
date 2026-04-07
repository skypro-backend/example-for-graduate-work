package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdvertisementEntity;

import java.util.List;

public interface AdService {
    List<Ad> getAllAds();
    Ad createAd(CreateOrUpdateAd adDto, MultipartFile image, String authorEmail);
    Ad getAdById(Long id);
    void deleteAd(Long id);
    AdvertisementEntity updateAd(Long id, CreateOrUpdateAd request);
    List<AdvertisementEntity> getMyAds(String email);
    AdvertisementEntity updateAdImage(Long id, MultipartFile file);
}
