package ru.skypro.homework.service;


import ru.skypro.homework.dto.*;

public interface AdService {
    ExtendedAd getAdById(int id) throws Exception;
    void deleteAd(int id) throws Exception;
    Ad updateAd(int id, CreateOrUpdateAd adDto) throws Exception;
    Ads getAdsForCurrentUser() throws Exception;
    void updateAdImage(int id, byte[] imageBytes) throws Exception;

}