package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

/**
 * Интерфейс для операций с объявлениями.
 * Включает создание, получение, обновление, удаление объявлений, а также работу с изображениями.
 */
public interface AdService {

    Ads getAllAds();

    Ad createAd(CreateOrUpdateAd adDto, MultipartFile image, String email);

    ExtendedAd getAdById(Integer id);

    void deleteAd(Integer id, String email);

    Ad updateAd(Integer id, CreateOrUpdateAd request, String email);

    Ads getMyAds(String email);

    byte[] updateAdImage(Integer id, MultipartFile file, String email);
}