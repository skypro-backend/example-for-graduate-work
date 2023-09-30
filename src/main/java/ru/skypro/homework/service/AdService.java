package ru.skypro.homework.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.util.List;
import java.util.Optional;

public abstract class AdService {
    public abstract Ad createAd(String userLogin, MultipartFile multipartFile, CreateOrUpdateAd createAd);

    public abstract List<Ad> getAllAds();

    public abstract Optional<ExtendedAd> getExtendedAdDto(Long id);

    @PreAuthorize("hasRole('ADMIN') or @permissionCheckServiceImpl.checkAdsUserName(#userLogin, #idAd)")
    public abstract boolean deleteByIdAd(String userLogin, Long idAd);

    @PreAuthorize("hasRole('ADMIN') or @permissionCheckServiceImpl.checkAdsUserName(#userLogin, #idAd)")
    public abstract Optional<Ad> updateAd(String userLogin, Long idAd, CreateOrUpdateAd updateAd);

    public abstract List<Ad> getMyAds(String userLogin);

    @PreAuthorize("hasRole('ADMIN') or @permissionCheckServiceImpl.checkAdsUserName(authenticated.name, #idAd)")
    public abstract Optional<String> changeImage(Long idAd, MultipartFile multipartFile);
}
