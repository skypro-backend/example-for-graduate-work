package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public AdEntity createAd(AdEntity createAd) {
        return adRepository.save(createAd);
    }

    @Override
    public AdEntity updateAd(AdEntity updateAd) {
        return adRepository.save(updateAd);
    }
}
