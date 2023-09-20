package ru.skypro.homework.service.ads;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.dto.ads.out.AdsDto;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.ExtendedAdDto;

import java.io.IOException;


public interface AdsService {

    AdsDto getAllAds();

    AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image);

    ExtendedAdDto getAds(Integer id);

    void removeAd(Integer id);

    AdDto updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);

    AdsDto getAdsMe();

    byte[] updateImage(Integer id, MultipartFile image) throws IOException;

    byte[] getImage(Integer id) throws IOException;
}
