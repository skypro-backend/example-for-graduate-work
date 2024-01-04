package ru.skypro.kakavito.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.*;
import ru.skypro.kakavito.exceptions.AdNotFoundException;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.exceptions.UserNotFoundException;
import ru.skypro.kakavito.mappers.AdMapper;
import ru.skypro.kakavito.model.Ad;
import ru.skypro.kakavito.model.User;
import ru.skypro.kakavito.model.Image;
import ru.skypro.kakavito.repository.AdRepo;
import ru.skypro.kakavito.repository.UserRepo;
import ru.skypro.kakavito.service.AdService;
import ru.skypro.kakavito.service.ImageService;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;
    private final AdRepo adRepo;
    private final UserRepo userRepo;
    private final ImageService imageService;
    private final Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);

    /**
     * Получает все объявления из репозитория.
     *
     * @return список всех объявлений.
     */
    @Override
    public AdsDTO findAllAds() {
        AdsDTO adsDTO = adMapper.convertToAdsDTO(adRepo.findAll());
        logger.info("AdService findAll is running");
//        adsDTO.getCount();
        return adsDTO;
    }

    /**
     * Создает новое объявление.
     *
     * @param createOrUpdateAdDTO DTO для создания объявления.
     * @param imageFile фотография предмета
     * @return созданное объявление.
     */
    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile imageFile) throws IOException, ImageSizeExceededException {
        Ad ad = adMapper.convertCreatDTOToAd(createOrUpdateAdDTO);
        ad.setUser(getAuthUser());
        Ad savedAd = adRepo.save(ad);
        Image image = imageService.upLoadImage(imageFile);
        savedAd.setImage(image);
        adRepo.save(savedAd);
        logger.info("AdService createAd is running");
        return adMapper.convertToAdDTO(savedAd);
    }

    /**
     * Получает объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return объявление.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Override
    public ExtendedAdDTO findById(int id) {
        return adRepo.findById(Math.toIntExact(id))
                .map(adMapper::convertToExtendedAd)
                .orElseThrow(() -> {
                    logger.error("AdService findById is running");
                    return new AdNotFoundException("Ad not found");
                });
    }


    @Override
    public AdsDTO getAdByAuthUser() {
        User user = getAuthUser();
        return adMapper.convertToAdsDTO(adRepo.findAllById(Math.toIntExact(user.getId())));
    }

    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }


    @Override
    public AdDTO updateAd(int id, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = adRepo.findById(Math.toIntExact(id)).orElseThrow(() -> new AdNotFoundException("Ad not found"));
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        ad.setTitle(createOrUpdateAdDTO.getTitle());
        return adMapper.convertToAdDTO(ad);
    }

    @Override
    public void updateAdImage(int id, MultipartFile imageFile) {
        imageService.updateImage(adRepo.findById(id).orElseThrow(() ->
        new AdNotFoundException("Ad not found")).getId(), imageFile);
    }

    @Override
    public void deleteAd(int id) {
        adRepo.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Ad not found"));
        adRepo.deleteById(id);
        logger.info("AdService deleteAd is running");
    }
}
