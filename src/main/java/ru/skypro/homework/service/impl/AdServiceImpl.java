package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public ExtendedAd getAdById(int id) {
        return null;
    }

    @Override
    public void deleteAd(int id) {
    }

    @Override
    public Ad updateAd(int id, CreateOrUpdateAd adDto) {
        return null;
    }

    @Override
    public Ads getAdsForCurrentUser() {
        return null;
    }

    @Override
    public void updateAdImage(int id, byte[] imageBytes) {
    }
}
