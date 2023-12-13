package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;


import java.util.List;

public interface AdService {
    public List<ru.skypro.homework.dto.ads.Ad> getAllAdsFromDatabase(String userName);
    public Ads allAdsPassToController();
    public AdEntity newAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username);
    public ExtendedAd requestAdFromDbById(int id);
    public AdEntity editPatch(CreateOrUpdateAd createOrUpdateAd, int id, String username);
    public Ads authorizedUserAds(String username);
    public boolean patchAdPictureById(MultipartFile image, int adId, String username);
    public boolean deleteAdById(int id, String username);
    public AdEntity callAdById(int id);


}
