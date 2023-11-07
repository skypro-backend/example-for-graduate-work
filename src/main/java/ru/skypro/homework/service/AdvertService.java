package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Advert;

import java.io.IOException;

public interface AdvertService {
    Advert find(int id);

    AdDto createAdvert(String username, CreateOrUpdateAdDto dto, MultipartFile image);

    ExtendedAdDto getAdvertById(int id);

    AdsDto getAdvert();

    AdsDto getAdvert(String username);

    AdsDto getAllAdverts();

    AdDto updateAdvert(String username, int id, CreateOrUpdateAdDto dto);

    String updateAdImage(String username, int id, MultipartFile image);

    void deleteAdvert(int id);

    Object updateAdvert(int id, CreateOrUpdateAdDto createOrUpdateAdDto);

    Object createAdvert(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image);

    void deleteAdvert(String username, int id);
    byte[] getAvatarImage(String filename);
    void updateUserImage(String username, MultipartFile image) throws IOException;
}
