package ru.skypro.homework.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import javax.xml.crypto.OctetStreamData;

public interface AdService {
    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO ad, MultipartFile image);

    ExtendedAdDTO getInfoByAd(int id);
    @PreAuthorize("@mySecurityService.canRefAdd(#id)")
    void deleteAd(int id);
    @PreAuthorize("@mySecurityService.canRefAdd(#id)")
    AdDTO updateInfoByAd(int id, CreateOrUpdateAdDTO ad);
    AdsDTO getAdsByAuthUser(String email);
    @PreAuthorize("@mySecurityService.canRefAdd(#id)")
    OctetStreamData updateImage(int id, MultipartFile image);

}
