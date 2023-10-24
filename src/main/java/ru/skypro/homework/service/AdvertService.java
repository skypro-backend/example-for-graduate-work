package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Advert;


public interface AdvertService {
    Advert find(int id);

    AdDto createAdvert(CreateOrUpdateAdDto dto, MultipartFile image);

    ExtendedAdDto getAdvertById(int id);

    AdsDto getAdvert();

    AdsDto getAllAdverts();

    AdDto updateAdvert(int id, CreateOrUpdateAdDto dto);

    String update(int id, MultipartFile image);

    void deleteAdvert(int id);
}
