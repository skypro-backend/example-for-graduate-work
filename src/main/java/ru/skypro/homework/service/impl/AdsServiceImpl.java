package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.util.UserAuthentication;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;

    private final UserRepository userRepository;

    private final ImageService imageService;

    private final AdsMapper adsMapper;

    private final UserAuthentication userAuthentication;

    /**
     * Метод получает все объявления из БД и конвертирует их в Ads Dto.
     *
     * @return возвращает ResponsEntity status с Ads Dto.
     */
    @Override
    public ResponseEntity<?> getAllAds() {
        List<AdEntity> adEntityList = adRepository.findAll();
        List<Ad> adList = adsMapper.adEntityListToAdList(adEntityList);
        Integer sizeList = adList.size();

        Ads ads = new Ads();
        ads.setCount(sizeList);
        ads.setResults(adList);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    /**
     * Метод добавляет новое объявление в БД.
     *
     * @param properties CreateOrUpdateAd DTO. Включает title, price и description объявления.
     * @param image      принимает изображение объявления.
     * @return возвращает ResponsEntity status с Ad Dto.
     */
    @Override
    public ResponseEntity<?> addAd(CreateOrUpdateAd properties, MultipartFile image) {
        AdEntity newAdEntity = adsMapper.createOrUpdateAdToAdEntity(properties);
        UserEntity currentUserEntity = userAuthentication.getCurrentUserName();

        if (currentUserEntity.getId() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            newAdEntity.setUserEntity(currentUserEntity);
            AdEntity savedAdEntity = adRepository.save(newAdEntity);

            try {
                imageService.uploadAdImage(savedAdEntity.getPk(), image);
            } catch (IOException e) {
                log.error("Image not uploaded");
            }
            return new ResponseEntity<>(adsMapper.adEntityToAd(savedAdEntity), HttpStatus.CREATED);
        }
    }

    /**
     * Метод получает из БД информацию об объявлении по id объявления.
     *
     * @param adPk идентификатор объявления в БД.
     * @return возвращает ResponsEntity status с ExtendedAd Dto.
     */
    @Override
    public ResponseEntity<?> getAds(Integer adPk) {
        Optional<AdEntity> checkForExistAd = adRepository.findById(adPk);

        if (checkForExistAd.isEmpty()) {
            log.error("Ad not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdEntity foundedAdEntity = checkForExistAd.get();
            if (foundedAdEntity.getUserEntity().getId() != null) {
                return new ResponseEntity<>(adsMapper.adEntityToExtendedAd(foundedAdEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }

    /**
     * Метод удаляет объявление из БД по id объявления.
     *
     * @param adPk идентификатор объявления в БД.
     * @return возвращает ResponsEntity status.
     */
    @Override
    public ResponseEntity<?> removeAd(Integer adPk) {
        Optional<AdEntity> checkForExistAd = adRepository.findById(adPk);

        if (checkForExistAd.isEmpty()) {
            log.error("Ad not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdEntity foundedAdEntity = checkForExistAd.get();
            if (foundedAdEntity.getUserEntity().getId() != null) {
                adRepository.deleteById(adPk);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        // TODO: 15.10.2023 не удаляется фото
        // TODO: 15.10.2023 добавить HttpStatus.FORBIDDEN
    }

    /**
     * Метод обновляет информацию об объявлении в БД по id объявления.
     *
     * @param adPk             идентификатор объявления в БД.
     * @param createOrUpdateAd DTO. Включает title, price и description объявления.
     * @return возвращает ResponsEntity status с Ad Dto.
     */
    @Override
    public ResponseEntity<?> updateAds(Integer adPk, CreateOrUpdateAd createOrUpdateAd) {
        Optional<AdEntity> checkForExistAd = adRepository.findById(adPk);

        if (checkForExistAd.isEmpty()) {
            log.error("Ad not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdEntity foundedAdEntity = checkForExistAd.get();
            if (foundedAdEntity.getUserEntity().getId() != null) {
                AdEntity updatedAdEntity = adsMapper.createOrUpdateAdToAdEntity(createOrUpdateAd);
                updatedAdEntity.setPk(foundedAdEntity.getPk());
                updatedAdEntity.setUserEntity(foundedAdEntity.getUserEntity());
                updatedAdEntity.setImageEntity(foundedAdEntity.getImageEntity());
                adRepository.save(updatedAdEntity);
                return new ResponseEntity<>(adsMapper.adEntityToAd(updatedAdEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        // TODO: 15.10.2023 добавить HttpStatus.FORBIDDEN
    }

    /**
     * Метод возвращает все объявления авторизованного пользователя.
     *
     * @return возвращает ResponsEntity status с Ads Dto.
     */
    @Override
    public ResponseEntity<?> getAdsMe() { // TODO: 15.10.2023 выкидывает PSQLException
        UserEntity currentUserEntity = userAuthentication.getCurrentUserName();

        if (currentUserEntity != null) {
            Collection<AdEntity> adEntityList = userRepository.findById(currentUserEntity.getId())
                                                            .map(UserEntity::getAdEntities)
                                                            .orElse(null);
            List<Ad> adList = adsMapper.adEntityListToAdList((List<AdEntity>) adEntityList);
            Integer sizeList = adList.size();

            Ads ads = new Ads();
            ads.setCount(sizeList);
            ads.setResults(adList);
            return new ResponseEntity<>(ads, HttpStatus.OK);
        } else {
            log.error("User not founded");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Метод обновляет в БД картинку объявления по id объявления.
     *
     * @param adPk   идентификатор объявления в БД.
     * @param file   изображение.
     * @return возвращает ResponsEntity status с byte[] (бинарным кодом изображения).
     */
    @Override
    public ResponseEntity<?> updateImage(Integer adPk, MultipartFile file) {
        Optional<AdEntity> checkForExistAd = adRepository.findById(adPk);

        if (checkForExistAd.isEmpty()) {
            log.error("Ad not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdEntity foundedAdEntity = checkForExistAd.get();

            if (foundedAdEntity.getUserEntity().getId() != null) {
                byte[] image = new byte[0];
                try {
                    image = imageService.uploadAdImage(adPk, file);
                } catch (IOException e) {
                    log.error("Image not uploaded");
                }
                return new ResponseEntity<>(image, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        // TODO: 15.10.2023 добавить HttpStatus.FORBIDDEN
    }

}
