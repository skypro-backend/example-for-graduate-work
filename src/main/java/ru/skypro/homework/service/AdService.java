package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

import java.util.List;

public interface AdService {

    AdDTO createAd(CreateOrUpdateAd createdAd, MultipartFile img);

    CreateOrUpdateAd updateAd(CreateOrUpdateAd createOrUpdateAd, int ad_id);

    AdDTO getAd(int ad_id);

    List<AdDTO> getAds();

    void deleteAd(int ad_id);

}
