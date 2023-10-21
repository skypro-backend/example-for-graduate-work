package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.projections.Ads;
import ru.skypro.homework.projections.CreateOrUpdateAd;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.repository.AdRepo;
import ru.skypro.homework.service.AdService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdRepo repo;


    @Override
    public Ads getAllAds() {
        List<AdDTO> adsList = repo.findAll().stream()
                .map(AdMapper::fromAdDto)
                .collect(Collectors.toList());
        return new Ads(adsList.size(), adsList);
    }

    public AdDTO addAd(CreateOrUpdateAd createOrUpdateAd, String pathImage) {
        AdModel ad = new AdModel();
        ad.setImage(pathImage);
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setDescription(createOrUpdateAd.getDescription());
        repo.save(ad);
        return AdMapper.fromAdDto(ad);
    }


    @Override
    public ExtendedAd getAds(int id) {
        return repo.getExtendedAd(id).orElseThrow(AdNotFoundException::new);

    }

    public Ads updateAd(int id, CreateOrUpdateAd createOrUpdateAdDTO) {
        return null;
    }

    @Override
    public void removeAd(int id) {
        Optional<AdModel> ad = repo.findById(id);
        if (ad.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new AdNotFoundException();
        }
    }

    @Override
    public Ads getAdsMe() {

        return null;
    }

    @Override
    public String updateImage(int id, String pathImage) {
        return null;
    }


}

