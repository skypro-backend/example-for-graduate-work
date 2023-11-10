package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;

import java.io.IOException;

public interface AdService {
    AdDto add(CreateOrUpdateAdDto properties, MultipartFile image, String email) throws IOException;

    ExtendedAdDto getFullAdsById(int id);

    AdsDto getAllAds();

    AdsDto getAllMyAds(String name);

    void delete(int id) throws IOException;

    AdDto update(int id, CreateOrUpdateAdDto ads);

    Ad getEntity(int id);

    void uploadImage(int id, MultipartFile image) throws IOException;

}
