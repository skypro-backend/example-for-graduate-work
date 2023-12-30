package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.BlankFieldException;
import ru.skypro.homework.exceptions.MissingAdException;
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
    private final ImageServiceImpl imageService;
    private final AuthenticationCheck authenticationCheck;

    @Override
    @Transactional
    public Ads getAllAds() {
        return new Ads(adEntityRepository.findAll().stream()
                .map(adMapper::AdEntityToAd)
                .collect(Collectors.toList()));
    }
    @Override
    public Ad addAd(MultipartFile image, CreateOrUpdateAd properties, CustomUserDetails userDetails) {

        UserEntity userEntity = userEntityRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found in database"));

        if (properties.getTitle().isEmpty() || properties.getPrice().toString().isEmpty()
                || properties.getDescription().isEmpty()) {
            throw new BlankFieldException("Empty fields saving an object CreateOrUpdateAd");
        }

        AdEntity adEntity = adMapper.AdToAdEntity(properties);
        adEntityRepository.save(adEntity); // сохраняем в базу чтобы получить Id обьявления для формирования названия изображения

        ImageEntity adImage = imageService.uploadImageToServer(image, adEntity.getId(), adEntity.getTitle());
        adEntity.setImageEntity(Optional.ofNullable(adImage).orElse(new ImageEntity()));
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

        AdEntity adEntity = adEntityRepository.findById(adId)
                .orElseThrow(() ->new EntityNotFoundException("The entity with the specified id = " + adId + " was not found"));
        authenticationCheck.accessCheck(userDetails, adEntity.getUserEntity());

        ImageEntity imageToRemove = adEntity.getImageEntity();

        commentEntityRepository.deleteAll(adEntity.getCommentEntities());
        adEntityRepository.delete(adEntity);
        imageEntityRepository.delete(imageToRemove);

        logger.info("The removeAd method removed the ad with the id = " + adId);
    }
    @Override
    public CreateOrUpdateAd updateAd(Integer adId, CreateOrUpdateAd properties, CustomUserDetails userDetails) {


        AdEntity adEntity = adEntityRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("The ad with id = " + adId + " was not found"));

        authenticationCheck.accessCheck(userDetails, adEntity.getUserEntity());

        if (!properties.getTitle().isEmpty()) {
            adEntity.setTitle(properties.getTitle());}
        if (!properties.getPrice().toString().isEmpty()) {
            adEntity.setPrice(properties.getPrice());}
        if (!properties.getDescription().isEmpty()) {
            adEntity.setDescription(properties.getDescription());}

        adEntityRepository.save(adEntity);

        logger.info("The editFaculty method has updated the ad data");
        return properties;
    }
    @Override
    public Ads getAdsMe(CustomUserDetails userDetails) {

        List<Ad> adList = adMapper.adEntityListToAdList(adEntityRepository.findByUserEntity_id(userDetails.getId()));
        return Ads.builder()
                .results(adList)
                .count(adList.size())
                .build();
    }
    @Override
    public void updateImage(Integer adId, MultipartFile image, CustomUserDetails userDetails) {

        AdEntity adEntity = adEntityRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Обьявление с указанным " + adId + " отсуствует!"));

        authenticationCheck.accessCheck(userDetails, adEntity.getUserEntity());

        ImageEntity obsoleteImage = adEntity.getImageEntity();
        ImageEntity newImage = imageService
                .uploadImageToServer(image, adEntity.getId(), adEntity.getTitle());
        adEntity.setImageEntity(newImage);
        adEntityRepository.save(adEntity);
        imageEntityRepository.delete(obsoleteImage);


        logger.info("The updateImage method updated the ad image" );
    }

}
