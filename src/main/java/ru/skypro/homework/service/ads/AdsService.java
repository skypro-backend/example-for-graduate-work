package ru.skypro.homework.service.ads;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.projection.CreateOrUpdateAd;

public interface AdsService {

    ResponseEntity<?> getAllAds();
    ResponseEntity<?> createAd(CreateOrUpdateAd properties, MultipartFile image);
    ResponseEntity<?> getAdFullInfo(Integer id);
    ResponseEntity<?> deleteAd(Integer id);
    ResponseEntity<?> updateAd(Integer id,CreateOrUpdateAd properties);
    ResponseEntity<?> getAllAdsByUser();
    ResponseEntity<?> updateImage(Integer id, MultipartFile image);

}
