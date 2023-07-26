package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

import java.util.List;
import java.util.Optional;

public interface AdsService {

    AdsDto createAd(String userLogin, MultipartFile multipartFile, CreateOrUpdateAd createAd);

    List<AdsDto> getAllAds();

    Optional<ExtendedAdDto> getExtendedAdDto(Long id);

    boolean deleteByIdAd(String userLogin, Long id);

    Optional<AdsDto> updateAd(Long id, CreateOrUpdateAd updateAd);

    List<AdsDto> getMyAds(String userLogin);

    Optional<String> changeImage(Long id, MultipartFile multipartFile);

    Optional<Ad> getAdById(Long id);

}
