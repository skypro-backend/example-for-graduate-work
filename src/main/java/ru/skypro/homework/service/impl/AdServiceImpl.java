package ru.skypro.homework.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.security.Principal;

public class AdServiceImpl implements AdService {
    AdMapper instance = Mappers.getMapper(AdMapper.class);

    @Override
    public AdEntity findById(Integer id) {
        return null;
    }

    @Override
    public AdsDTO getMyAds(Principal principal) {
        return null;
    }

    @Override
    public AdsDTO getAllAds() {
        return null;
    }

    @Override
    public ExtendedAdDTO getInfoAboutAd(Integer adId) {
        return null;
    }

    @Override
    public Boolean deleteAd(Integer adId) {
        return null;
    }

    @Override
    public byte[] updateImageInAd(MultipartFile image, Integer adId) throws IOException {
        return new byte[0];
    }
}
