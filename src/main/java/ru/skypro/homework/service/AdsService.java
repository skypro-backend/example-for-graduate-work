package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;

public interface AdsService {

    ResponseEntity<?> getAllAds();

    ResponseEntity<?> addAd(CreateOrUpdateAd properties, MultipartFile image);

    ResponseEntity<?> getAds(Integer adPk);

    ResponseEntity<?> removeAd(Integer adPk);

    ResponseEntity<?> updateAds(Integer adPk, CreateOrUpdateAd createOrUpdateAd);

    ResponseEntity<?> getAdsMe();

    ResponseEntity<?> updateImage(Integer adPk, MultipartFile file);

}
