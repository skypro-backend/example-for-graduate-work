package ru.skypro.homework.service;


import ru.skypro.homework.dto.*;

import java.util.Collection;

public interface AdService {
    Collection<AdDto> getAll();
    boolean addAd(CreateOrUpdateAdDto adDto);
    ExtendedAdDto getAdById(int id) throws Exception;
    void deleteAd(int id) throws Exception;
    AdDto updateAd(int id, CreateOrUpdateAdDto adDto) throws Exception;
    AdsDto getAdsForCurrentUser() throws Exception;
    void updateAdImage(int id, byte[] imageBytes) throws Exception;

}