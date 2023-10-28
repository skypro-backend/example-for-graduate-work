package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

public interface AdService {

    void removeAd(Integer id);

    AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image);

    ExtendedAdDto getAdById(Integer id);

    AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);

}
