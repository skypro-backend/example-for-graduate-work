package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;

public interface AdsService {

//    Ad createAds(Ad ad);

    AdsDTO createAds(CreateAds createAds, MultipartFile file)  throws UserNotFoundException;

    Ad getAdById(int id);

    void deleteAd(int id);

    ResponseWrapperAds getAllAds();

    ResponseWrapperAds getAllUserAds();

    AdsDTO updateAd(int id, CreateAds createAds);

    AdsDTO updateAdImage(Integer adId, MultipartFile file) throws UserNotFoundException;

    FullAds getFullAdById(int adId);
}
