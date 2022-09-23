package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;

import java.io.IOException;
import java.util.List;

public interface AdsService {

    List<AdsDto> getALLAds();

    AdsDto addAds(CreateAdsDto ads, MultipartFile file) throws IOException;

    List<AdsDto> getAdsMe(Boolean authenticated, String authority, Object credentials, Object details, Object principal);

    void removeAds(Integer id);

    FullAdsDto getAds(Integer id);

    AdsDto updateAds(Integer id, AdsDto ads);

}
