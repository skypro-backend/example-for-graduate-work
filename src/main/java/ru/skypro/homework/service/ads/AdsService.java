package ru.skypro.homework.service.ads;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.projection.ExtendedAd;

import java.util.List;

public interface AdsService {

    List<AdDTO> getAllAds();
    AdDTO createAd(CreateOrUpdateAd properties, MultipartFile image);
    ExtendedAd getAdFullInfo(Integer id);
    void deleteAd(Integer id);
    ResponseEntity<?> updateAd(Integer id,CreateOrUpdateAd properties);
    List<AdDTO> getAllAdsByUser(String user);
    ResponseEntity<?> updateImage(Integer id, MultipartFile image);

}
