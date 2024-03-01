package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.model.Ad;

import java.util.List;


public interface AdService {
    List<Ad> getAllAds();

    Ad getAdById(Long id);

    Ad createAd(CreateOrUpdateAd dto, MultipartFile imageFile);

    Ad updateAd(Long id, CreateOrUpdateAd dto);

    void deleteAd(Long id);

    List<Ad> getMyAds();

    Ad updateAdImage(Long id, MultipartFile imageFile);
}
