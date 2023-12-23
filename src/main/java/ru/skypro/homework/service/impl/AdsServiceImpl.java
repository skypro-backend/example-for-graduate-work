package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.BlankFieldException;
import ru.skypro.homework.exceptions.MissingImageException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.helper.AuthenticationCheck;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {
    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);
    private final String imageDir;
    private AdEntityRepository adEntityRepository;
    private AdMapper adMapper;
    private UserEntityRepository userEntityRepository;
    private final AuthenticationCheck authenticationCheck;
//    private ImageEntity imageEntity;

    public AdsServiceImpl(AdEntityRepository adEntityRepository, AdMapper adMapper,
                          @Value("${path.to.images.folder}") String imageDir,
                          UserEntityRepository userEntityRepository,
                          AuthenticationCheck authenticationCheck) {
        this.adEntityRepository = adEntityRepository;
        this.adMapper = adMapper;
        this.imageDir = imageDir;
        this.userEntityRepository = userEntityRepository;
        this.authenticationCheck = authenticationCheck;
//        this.imageEntity = imageEntity;
    }
    @Override
    public Ads getAllAds() {

        List<AdEntity> adEntityList = adEntityRepository.findAll();
        if (adEntityList.isEmpty()) {
            logger.warn("The list of ads is empty!");
            return null;
        }

        List<Ad> adList = adMapper.adEntityListToAdList(adEntityList);
        logger.info("The list of ads has been returned!");
        return Ads.builder()
                .results(adList)
                .count(adList.size())
                .build();
    }
    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found in database"));

        if (properties.getTitle() == null || properties.getPrice() == null
                || properties.getDescription() == null) {
            throw new BlankFieldException("Empty fields when saving an object CreateOrUpdateAd ");
        } else if (image.isEmpty()) {
            throw new MissingImageException("Ad image is missing");
        }

        AdEntity adEntity = adMapper.AdToAdEntity(properties);
// туДу поменять строки внутри комментариев на метод по обработке фото
        adEntity.setImageEntity(null);
//
        adEntity.setUserEntity(userEntity);
        adEntityRepository.save(adEntity);
        logger.info("A user's ad with a username (email) " + userDetails.getUsername() + " has been added");
        return adMapper.AdEntityToAd(adEntity);
    }
    @Override
    public ExtendedAd getAds(Integer adId) {

        ExtendedAd extendedAd = adMapper
                .AdEntityToExtendedAd(adEntityRepository.findById(adId).get());
        if (extendedAd.getPk() == null) {
            throw new EntityNotFoundException("The ad with the specified id was not found");
        }
        logger.info("The ad with the specified id = " +  adId +  " was returned" );
        return extendedAd;
    }

    public void removeAd(Integer adId, CustomUserDetails userDetails) {
        logger.info("The removeAd method was called with id =" + adId);

        Optional<AdEntity> adEntity = adEntityRepository.findById(adId);

        if (adEntity.isEmpty()) {
            throw new EntityNotFoundException("The entity with the specified id = " + adId + " was not found");
        }

        authenticationCheck.accessCheck(userDetails, adEntity.get().getUserEntity());
        adEntityRepository.deleteById(adId);

        logger.info("The removeAd method removed the ad with the id = " + adId);
    }
    @Override
    public CreateOrUpdateAd updateAd(Integer adId, CreateOrUpdateAd properties, CustomUserDetails userDetails) {

        logger.info("The editFaculty method was called with the new ad data " + properties);

        AdEntity adEntity = adEntityRepository.findById(userDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("The ad with id = " + adId + " was not found"));

        authenticationCheck.accessCheck(userDetails, adEntity.getUserEntity());

        if (properties.getTitle() != null) {
            adEntity.setTitle(properties.getTitle());
        } else if (properties.getPrice() != null) {
            adEntity.setPrice(properties.getPrice());
        } else if (properties.getDescription() != null) {
            adEntity.setDescription(properties.getDescription());
        }

        adEntityRepository.save(adEntity);

        logger.info("The editFaculty method has updated the ad data");
        return properties;
    }
    @Override
    public Ads getAdsMe(CustomUserDetails userDetails) {

        List<AdEntity> adEntityList = adEntityRepository.findByUserEntity_id(userDetails.getId());

        if (adEntityList.isEmpty()) {
            throw new EntityNotFoundException("The current user has no ads!");
        }

        List<Ad> adList = adMapper.adEntityListToAdList(adEntityList);
        logger.info("The getAdsMe method returns a list of the user's ads with the user's username (email) = " + userDetails.getUsername());
        return Ads.builder()
                .results(adList)
                .count(adList.size())
                .build();
    }
    @Override
    public void updateImage(Integer adId, MultipartFile image, CustomUserDetails userDetails) {

        logger.info("Был вызван метод updateImage для обьявления с adId" + adId);
        AdEntity adEntity = adEntityRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Обьявление с указанным " + adId + " отсуствует!"));

        authenticationCheck.accessCheck(userDetails, adEntity.getUserEntity());

        if (!image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/png") && !image.getContentType().equals("image/gif") ) {
            throw new MissingImageException("Некорректный формат изображения обьявления!");
        }

        // туДу поменять строки внутри комментариев на метод по обработке фото
        adEntity.setImageEntity(null);
        //

        logger.info("Метод updateImage вернул адрес обновленного фото: " ); // туДу добавить путь
    }

}
