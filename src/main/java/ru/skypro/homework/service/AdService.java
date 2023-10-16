package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.AdsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;

public interface AdService {
    public AdsDto getAllAds();

    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image);

    public ExtendedAdDto getAdInformation(Integer id);

    public void deleteAd(Integer id);

    public AdDto updateAdInformation(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);

    public AdsDto getAuthorizedUserAds();
}
