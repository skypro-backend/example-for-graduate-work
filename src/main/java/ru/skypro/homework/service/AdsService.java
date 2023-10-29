package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.repository.AdRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AdsService {
    private static Logger logger = LoggerFactory.getLogger(AdsService.class);
    private final ModelMapper mapper;
    private final AdRepository adRepository;

    private AdEntity adToAdEntity(Ad ad) {
        return mapper.map(ad, AdEntity.class);
    }

    private Ad adEntityToAd(AdEntity adEntity) {
        return mapper.map(adEntity, Ad.class);
    }

    public Ad addAd(CreateOrUpdateAd createOrUpdateAd) {
        logger.info("Ad was successfully added: {}", createOrUpdateAd.getTitle());
        return new Ad(); // Temp
    }

    public Ads getAllAds() {
        List<Ad> allAd = adRepository.findAll().stream().map(a -> mapper.map(a, Ad.class)).collect(Collectors.toList());
        Ads ads = new Ads();
        ads.setCount(allAd.size());
        ads.setResults(allAd);
        logger.info("Number of ads sent: {}", ads.getCount());
        return ads;
    }

}
