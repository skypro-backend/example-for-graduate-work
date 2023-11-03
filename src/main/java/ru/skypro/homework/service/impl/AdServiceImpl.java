package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.ImageNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.AdsUserDetails;
import ru.skypro.homework.model.ImageModel;
import ru.skypro.homework.projections.Ads;
import ru.skypro.homework.projections.CreateOrUpdateAd;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.repository.AdRepo;
import ru.skypro.homework.repository.ImageRepo;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.skypro.homework.mapper.AdMapper.getExtendedAd;
import static ru.skypro.homework.mapper.AdMapper.toAdDto;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdRepo adRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ImageRepo imageRepo;

    /**
     * получение всех объявлений
     */

    @Override
    public Ads getAllAds() {
        List<AdDTO> adsList = adRepo.findAll().stream()
                .map(AdMapper::toAdDto)
                .collect(Collectors.toList());
        return new Ads(adsList.size(), adsList);
    }

    /**
     * создание объявления
     */
    @Transactional
    public AdDTO addAd(CreateOrUpdateAd properties, MultipartFile file, Authentication authentication) {
        AdsUserDetails adsUserDetails = (AdsUserDetails) authentication.getPrincipal();
        String username = authentication.getName();
//        UserModel user = userRepo.findByUserName(username).orElseThrow(UserNotFoundException::new);
        ImageModel imageModel = new ImageModel();
        imageModel.setId(UUID.randomUUID().toString());
        try {
            imageModel.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageRepo.save(imageModel);

        AdModel adModel = new AdModel();
        adModel.setImage(imageModel);
        adModel.setPrice(properties.getPrice());
        adModel.setTitle(properties.getTitle());
        adModel.setDescription(properties.getDescription());
        adModel.setUserModel(adsUserDetails.getUser());
        adRepo.save(adModel);
        return AdMapper.toAdDto(adModel);
    }

    /**
     * получение полной информации об объявлении
     */

    @Override
    public ExtendedAd getAds(int id) {
        AdModel ad = adRepo.findById(id).orElseThrow(AdNotFoundException::new);
        return getExtendedAd(ad);
    }

    /**
     * внесение изменений в объявление
     */
    @Transactional
    @Override
    public AdDTO updateAd(int id, CreateOrUpdateAd createOrUpdateAdDTO) {
        AdModel adModel = adRepo.findById(id).orElseThrow(AdNotFoundException::new);
        adModel.setTitle(createOrUpdateAdDTO.getTitle());
        adModel.setPrice(createOrUpdateAdDTO.getPrice());
        adModel.setDescription(createOrUpdateAdDTO.getDescription());
        adRepo.saveAndFlush(adModel);
        return toAdDto(adModel);
    }

    /**
     * удаление объявления
     */
    @Transactional
    @Override
    public void removeAd(int id) {
        if (adRepo.findById(id).isEmpty()) {
            throw new AdNotFoundException();
        }
        adRepo.deleteById(id);
    }

    /**
     * получение объявлений авторизированного пользователя
     */
    @Override
    public Ads getAdsMe(int userId) {
        List<AdDTO> list = adRepo.findAll().stream()
                .filter(adModel -> adModel.getUserModel().getId() == userId)
                .map(AdMapper::toAdDto)
                .collect(Collectors.toList());
        return new Ads(list.size(), list);
    }

    /**
     * изменение картинки объявления
     */
    @Transactional
    @Override
    public String updateImage(int id, MultipartFile file) {
        AdModel ad = adRepo.findById(id).orElseThrow(AdNotFoundException::new);

        ImageModel imageModel = imageRepo.findById(ad.getImage().getId()).orElseThrow(ImageNotFoundException::new);
        try {
            imageModel.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageRepo.saveAndFlush(imageModel);
        return ("/image/" + imageModel.getId());
    }


}

