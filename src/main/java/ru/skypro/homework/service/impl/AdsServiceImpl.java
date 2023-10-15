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
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
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

    private final ImageRepository imageRepository;

    private final UserAuthentication userAuthentication;

    @Override
    public ResponseEntity<?> getAllAds() {
        List<AdEntity> adEntityList = adRepository.findAll();

        // TODO: 14.10.2023 доработать AdsMapper
        List<Ad> adList = AdsMapper.INSTANCE.adEntityListToAdList(adEntityList);
        Integer sizeList = adList.size();

        Ads ads = new Ads();
        ads.setCount(sizeList);
        ads.setResults(adList);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addAd(CreateOrUpdateAd properties, MultipartFile image) {
        AdEntity newAdEntity = AdsMapper.INSTANCE.createOrUpdateAdToAdEntity(properties);
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

            // TODO: 14.10.2023 доработать AdsMapper
            String filePath = imageRepository.findFilePathByAdEntityPk(savedAdEntity.getPk());
            Ad ad = AdsMapper.INSTANCE.adEntityToAd(savedAdEntity);
            ad.setAuthor(savedAdEntity.getUserEntity().getId());
            ad.setImage(filePath);
            return new ResponseEntity<>(ad, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> getAds(Integer adPk) {
        Optional<AdEntity> foundedAd = adRepository.findById(adPk);

        if (foundedAd.isEmpty()) {
            log.error("Ad not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdEntity adEntity = foundedAd.get();
            if (adEntity.getUserEntity().getId() != null) {

                // TODO: 14.10.2023 доработать AdsMapper
                String filePath = imageRepository.findFilePathByAdEntityPk(adPk);
                ExtendedAd extendedAd = AdsMapper.INSTANCE.adEntityToExtendedAd(adEntity);
                extendedAd.setAuthorFirstName(adEntity.getUserEntity().getFirstName());
                extendedAd.setAuthorLastName(adEntity.getUserEntity().getLastName());
                extendedAd.setEmail(adEntity.getUserEntity().getUsername());
                extendedAd.setPhone(adEntity.getUserEntity().getPhone());
                extendedAd.setImage(filePath);
                return new ResponseEntity<>(extendedAd, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @Override
    public ResponseEntity<?> removeAd(Integer adPk) {
        Optional<AdEntity> foundedAd = adRepository.findById(adPk);

        if (foundedAd.isEmpty()) {
            log.error("Ad not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdEntity adEntity = foundedAd.get();
            if (adEntity.getUserEntity().getId() != null) {
                adRepository.deleteById(adPk);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        // TODO: 15.10.2023 не удаляется фото
        // TODO: 15.10.2023 добавить HttpStatus.FORBIDDEN
    }

    @Override
    public ResponseEntity<?> updateAds(Integer adPk, CreateOrUpdateAd createOrUpdateAd) {
        Optional<AdEntity> foundedAd = adRepository.findById(adPk);

        if (foundedAd.isEmpty()) {
            log.error("Ad not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdEntity adEntity = foundedAd.get();
            if (adEntity.getUserEntity().getId() != null) {
                AdEntity updatedAd = AdsMapper.INSTANCE.createOrUpdateAdToAdEntity(createOrUpdateAd);
                updatedAd.setPk(adPk);
                adRepository.save(updatedAd);

                // TODO: 14.10.2023 доработать AdsMapper
                String filePath = imageRepository.findFilePathByAdEntityPk(adPk);
                Ad ad = AdsMapper.INSTANCE.adEntityToAd(updatedAd);
                ad.setAuthor(updatedAd.getUserEntity().getId());
                ad.setImage(filePath);
                return new ResponseEntity<>(ad, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        // TODO: 15.10.2023 добавить HttpStatus.FORBIDDEN
    }

    @Override
    public ResponseEntity<?> getAdsMe() {
        UserEntity currentUserEntity = userAuthentication.getCurrentUserName();

        if (currentUserEntity != null) {
            Collection<AdEntity> adEntityList = userRepository.findById(currentUserEntity.getId())
                    .map(UserEntity::getAdEntities)
                    .orElse(null);

            // TODO: 14.10.2023 доработать AdsMapper
            List<Ad> adList = AdsMapper.INSTANCE.adEntityListToAdList((List<AdEntity>) adEntityList);
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

    @Override
    public ResponseEntity<?> updateImage(Integer adPk, MultipartFile file) {
        Optional<AdEntity> foundedAd = adRepository.findById(adPk);

        if (foundedAd.isEmpty()) {
            log.error("Ad not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdEntity adEntity = foundedAd.get();

            if (adEntity.getUserEntity().getId() != null) {
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
