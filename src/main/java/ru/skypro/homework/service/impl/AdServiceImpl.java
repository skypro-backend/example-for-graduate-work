package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {
    private static final String AD_NOT_FOUND_MSG = "Объявление с id: %d не найдено в БД";

    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Ad getADById(Integer adId) {
        return adRepository.findById(adId).orElseThrow(() -> new AdNotFoundException(String.format(AD_NOT_FOUND_MSG, adId)));
    }
}
