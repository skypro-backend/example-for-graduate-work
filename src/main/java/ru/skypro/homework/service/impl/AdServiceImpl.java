package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private static final Logger log = LoggerFactory.getLogger(AdServiceImpl.class);
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;

    public AdServiceImpl(AdRepository adRepository,
                         UserRepository userRepository,
                         AdMapper adMapper,
                         ImageService imageService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
    }

    @Override
    public Ads getAllAds() {
        log.debug("Getting all ads");
        List<AdEntity> adEntities = adRepository.findAll();
        List<Ad> ads = adEntities.stream()
                .map(ad -> {
                    Ad adDto = adMapper.toDto(ad);
                    if (ad.getImage() != null) {
                        adDto.setImage(imageService.getImageUrl(ad.getImage()));
                    }
                    return adDto;
                })
                .collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);
        return result;
    }

    @Override
    @Transactional
    public Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username) {
        log.debug("Adding new ad by user: {}", username);
        UserEntity author = findUserByUsername(username);

        // Сохраняем картинку
        String fileName = "ad_" + System.currentTimeMillis();
        String imagePath = imageService.saveImage(image, "ads", fileName);

        AdEntity adEntity = adMapper.createAdEntity(createOrUpdateAd, author, imagePath);
        adEntity = adRepository.save(adEntity);

        Ad adDto = adMapper.toDto(adEntity);
        adDto.setImage(imageService.getImageUrl(imagePath));
        return adDto;
    }

    @Override
    public ExtendedAd getAd(Integer id) {
        log.debug("Getting ad by id: {}", id);
        AdEntity adEntity = findAdById(id);
        ExtendedAd extendedAd = adMapper.toExtendedDto(adEntity);
        if (adEntity.getImage() != null) {
            extendedAd.setImage(imageService.getImageUrl(adEntity.getImage()));
        }
        return extendedAd;
    }

    @Override
    @Transactional
    public void removeAd(Integer id, String username) {
        log.debug("Removing ad with id: {} by user: {}", id, username);
        AdEntity adEntity = findAdById(id);
        checkAccess(adEntity, username);

        // Удаляем картинку
        if (adEntity.getImage() != null) {
            imageService.deleteImage(adEntity.getImage());
        }

        adRepository.delete(adEntity);
    }

    @Override
    @Transactional
    public Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd, String username) {
        log.debug("Updating ad with id: {} by user: {}", id, username);
        AdEntity adEntity = findAdById(id);
        checkAccess(adEntity, username);
        adMapper.updateAdEntity(createOrUpdateAd, adEntity);
        adEntity = adRepository.save(adEntity);

        Ad adDto = adMapper.toDto(adEntity);
        if (adEntity.getImage() != null) {
            adDto.setImage(imageService.getImageUrl(adEntity.getImage()));
        }
        return adDto;
    }

    @Override
    public Ads getAdsMe(String username) {
        log.debug("Getting ads for user: {}", username);
        UserEntity author = findUserByUsername(username);
        List<AdEntity> adEntities = adRepository.findByAuthor(author);
        List<Ad> ads = adEntities.stream()
                .map(ad -> {
                    Ad adDto = adMapper.toDto(ad);
                    if (ad.getImage() != null) {
                        adDto.setImage(imageService.getImageUrl(ad.getImage()));
                    }
                    return adDto;
                })
                .collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);
        return result;
    }

    @Override
    @Transactional
    public String updateImage(Integer id, MultipartFile image, String username) {
        log.debug("Updating image for ad id: {} by user: {}", id, username);
        AdEntity adEntity = findAdById(id);
        checkAccess(adEntity, username);

        // Удаляем старую картинку
        if (adEntity.getImage() != null) {
            imageService.deleteImage(adEntity.getImage());
        }

        // Сохраняем новую картинку
        String fileName = "ad_" + id;
        String imagePath = imageService.saveImage(image, "ads", fileName);
        adEntity.setImage(imagePath);
        adRepository.save(adEntity);

        return imageService.getImageUrl(imagePath);
    }

    @Override
    public boolean isAuthor(String username, Integer adId) {
        return adRepository.findById(adId)
                .map(ad -> ad.getAuthor().getUsername().equals(username))
                .orElse(false);
    }

    private AdEntity findAdById(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + id));
    }

    private UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    private void checkAccess(AdEntity adEntity, String username) {
        if (!adEntity.getAuthor().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not the author of this ad");
        }
    }
}