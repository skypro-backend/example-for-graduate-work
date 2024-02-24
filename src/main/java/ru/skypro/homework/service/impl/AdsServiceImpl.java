package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.exception.AdNotFound;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Collection;

@Service
@Slf4j
public class AdsServiceImpl implements AdsService {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserService userService;
    private final ImageService imageService;
    private final UserMapper userMapper;



    public AdsServiceImpl(AdRepository adRepository, AdMapper adMapper, UserService userService, ImageService imageService, UserMapper userMapper) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.userService = userService;
        this.imageService = imageService;
        this.userMapper = userMapper;
    }

    @Override
    public Collection<Ads> getAllAds() {

        return null;

    }

    @Override
    public Collection<AdEntity> getAdsMe(Authentication authentication) {
        return null;
    }



    @Override
    public CreateOrUpdateAd addAds(CreateOrUpdateAd createOrUpdateAd, MultipartFile imageFile, Authentication authentication) throws IOException {
        AdEntity adEntity = adMapper.toEntity(createOrUpdateAd);
        User user = userService.getUser(authentication);
        adEntity.setAuthor(userMapper.toEntity(user));
        adEntity.setImage(imageService.uploadImage(imageFile));
        adRepository.save(adEntity);
        return adMapper.toCreateOrUpdateAd(adEntity);
    }

    @Override
    public AdEntity removeAdsById(Long id) {
        return null;
    }

    @Override
    public AdEntity updateAds(Long adId, CreateOrUpdateAd createOrUpdateAd, Authentication authentication) {
        return null;
    }

    @Override
    public void updateAdsImage(long id, MultipartFile image, Authentication authentication) {

    }

    @Override
    public ExtendedAd getAds(Long id) {
    AdEntity adEntity = adRepository.findById(id).orElseThrow(AdNotFound::new);
        return adMapper.toExtendedAd(adEntity);
    }


}
