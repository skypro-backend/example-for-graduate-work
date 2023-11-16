package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ExtendedAd;

import java.util.List;


public interface AdService {

    Ad addAd(MultipartFile image, CreateOrUpdateAd adDetails);

    List<Ad> getAllAds();

    ExtendedAd getAdById(Long id);

    boolean removeAd(Long id);

    Ad updateAd(Long id, CreateOrUpdateAd adDetails);

    List<Ad> getAdsByCurrentUser();

    byte[] updateAdImage(Long id, MultipartFile image);
}
