package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.model.Image;

import java.util.List;

public interface AdsService {

    List<AdsDTO> getAllAds();

    AdsDTO addAds(MultipartFile image, CreateAdsDTO createAdsDTO);

    AdsInfoDTO getAdsById(long id);

    void deleteAds(long id);

    AdsDTO updateAds(long id, CreateAdsDTO createAdsDTO);

    List<AdsDTO> getUserAds();

   void updateAdsImage(long id, MultipartFile image);

    byte[] getImage(String id);
}
