package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

import java.util.Collection;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Override
    public Collection<AdDto> getAll() {
        return null;
    }
    @Override
    public boolean addAd(CreateOrUpdateAdDto adDto) {
        return true;  }
    @Override
    public ExtendedAdDto getAdById(int id) {
        return null;
    }
    @Override
    public void deleteAd(int id) {
    }

    @Override
    public AdDto updateAd(int id, CreateOrUpdateAdDto adDto) {
        return null;
    }

    @Override
    public AdsDto getAdsForCurrentUser() {
        return null;
    }

    @Override
    public void updateAdImage(int id, byte[] imageBytes) {
    }
}
