package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
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
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.BlankFieldException;
import ru.skypro.homework.exceptions.MissingAdException;
import ru.skypro.homework.exceptions.MissingImageException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.CommentEntityRepository;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.helper.AuthenticationCheck;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);
    private final AdEntityRepository adEntityRepository;
    private final AdMapper adMapper;
    private final UserEntityRepository userEntityRepository;
    private final CommentEntityRepository commentEntityRepository;
    private final ImageEntityRepository imageEntityRepository;
    private final AuthenticationCheck authenticationCheck;

//    public AdsServiceImpl(AdEntityRepository adEntityRepository, AdMapper adMapper,
//                          UserEntityRepository userEntityRepository,
//                          AuthenticationCheck authenticationCheck) {
//        this.adEntityRepository = adEntityRepository;
//        this.adMapper = adMapper;
//        this.userEntityRepository = userEntityRepository;
//        this.authenticationCheck = authenticationCheck;
//    }
    @Override
    @Transactional
    public Ads getAllAds() {

        return new Ads(adEntityRepository.findAll().stream()
                .map(adMapper::AdEntityToAd)
                .collect(Collectors.toList()));
    }
    @Override
    public Ad addAd(CreateOrUpdateAd properties, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found in database"));

        if (properties.getTitle() == null || properties.getPrice() == null
                || properties.getDescription() == null) {
            throw new BlankFieldException("Empty fields when saving an object CreateOrUpdateAd ");
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
        AdEntity adEntity = adEntityRepository.findById(adId)
                .orElseThrow(()->new MissingAdException("Ad with current id is not found"));
        return adMapper.AdEntityToExtendedAd(adEntity);
    }
    @Override
    public void removeAd(Integer adId, CustomUserDetails userDetails) {
        logger.info("The removeAd method was called with id =" + adId);

        AdEntity adEntity = adEntityRepository.findById(adId)
                .orElseThrow(() ->new EntityNotFoundException("The entity with the specified id = " + adId + " was not found"));

        authenticationCheck.accessCheck(userDetails, adEntity.getUserEntity());

        commentEntityRepository.deleteAll(adEntity.getCommentEntities());
        adEntityRepository.delete(adEntity);
        imageEntityRepository.delete(adEntity.getImageEntity());

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
