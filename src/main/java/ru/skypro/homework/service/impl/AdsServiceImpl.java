package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;

    @Override
    public List<AdsDto> getALLAds() {
        return new ArrayList<>();
    }

    @Override
    public AdsDto addAds(CreateAdsDto ads) {
        return null;
    }

    @Override
    public List<AdsDto> getAdsMe(Boolean authenticated, String authority, Object credentials, Object details, Object principal) {
        return null;
    }

    @Override
    public void removeAds(Integer id) {

    }

    @Override
    public FullAdsDto getAds(Integer id) {
        return null;
    }

    @Override
    public AdsDto updateAds(Integer id, AdsDto ads) {
        return null;
    }

}
