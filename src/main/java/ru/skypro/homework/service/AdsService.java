package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;
import ru.skypro.homework.dto.AllAdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.model.Image;

import java.util.List;

/**
 * Интерфейс, содержащий методы для работы с классом {@link ru.skypro.homework.model.Ads}
 * @see ru.skypro.homework.service.impl.AdsServiceImpl
 */
public interface AdsService {

    AllAdsDTO getAllAds();

    AdsDTO addAds(MultipartFile image, CreateAdsDTO createAdsDTO);

    AdsInfoDTO getAdsById(Integer id);

    void deleteAds(Integer id);

    AdsDTO updateAds(Integer id, CreateAdsDTO createAdsDTO);

    AllAdsDTO getUserAds();

    void updateAdsImage(Integer id, MultipartFile image);

    byte[] getImage(String id);
}
