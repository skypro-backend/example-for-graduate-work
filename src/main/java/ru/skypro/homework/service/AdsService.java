package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdInfoDTO;
import ru.skypro.homework.pojo.Image;

import java.io.IOException;
import java.util.List;

public interface AdsService {
    AdDTO createAd(Long userId, AdDTO adDTO, MultipartFile imageFile);

    List<AdDTO> getAllAds();

    AdInfoDTO getAdsInfo(Long pk);

    String deleteAd(Long pk);

    AdDTO updateAd(Long pk, AdDTO adDTO);

    List<AdDTO> getAdsForUser(Long userId);


    void updateAdImage(Long pk, Image newImage) throws IOException;
}
