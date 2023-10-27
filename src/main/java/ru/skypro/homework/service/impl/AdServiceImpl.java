package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.projections.Ads;
import ru.skypro.homework.projections.CreateOrUpdateAd;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.repository.AdRepo;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.AdService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdRepo adRepo;
    @Autowired
    UserRepo userRepo;

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
    public AdDTO addAd(CreateOrUpdateAd createOrUpdateAd, String pathImage, String user) {
        AdModel adModel = new AdModel();
        adModel.setImage("Test-path"); // потом удалить
//        adModel.setImage(pathImage);
        adModel.setPrice(createOrUpdateAd.getPrice());
        adModel.setTitle(createOrUpdateAd.getTitle());
        adModel.setDescription(createOrUpdateAd.getDescription());
        adModel.setUserModel(userRepo.findByUserName(user)
                .orElseThrow(() -> new UsernameNotFoundException("Not found")));
        adRepo.save(adModel);
        return AdMapper.toAdDto(adModel);
    }

    /**
     * получение полной информации об объявлении
     */

    @Override
    public ExtendedAd getAds(int id) {
// позже удалить  System.out.println(userRepo.getExtendedAd(id).orElseThrow(AdNotFoundException::new));
        return userRepo.getExtendedAd(id).orElseThrow(AdNotFoundException::new);
    }

    /**
     * внесение изменений в объявление
     */
    public Ads updateAd(int id, CreateOrUpdateAd createOrUpdateAdDTO) {
        return null;
    }

    /**
     * удаление объявления
     */
    @Override
    public void removeAd(int id) {
        Optional<AdModel> ad = adRepo.findById(id);
        if (ad.isPresent()) {
            adRepo.deleteById(id);
        } else {
            throw new AdNotFoundException();
        }
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
    @Override
    public String updateImage(int id, String pathImage) {
        return null;
    }


}

