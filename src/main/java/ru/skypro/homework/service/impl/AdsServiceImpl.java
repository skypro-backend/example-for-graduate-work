package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.mappers.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;

    private final AdsMapper adsMapper;

    @Override
    public List<AdsDto> getALLAds() {
        return adsRepository.findAll()
                .stream()
                .map(e -> adsMapper.toAdsDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public CreateAdsDto addAds(CreateAdsDto ads) {
        Ads newAds = adsMapper.fromCreateAdsToAds(ads);
        adsRepository.save(newAds);
        return ads;
    }

    @Override
    public List<AdsDto> getAdsMe(Boolean authenticated, String authority, Object credentials, Object details, Object principal) {
        // FIXME: Just returns all ads
        return adsRepository.findAll()
                .stream()
                .map(e -> adsMapper.toAdsDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public void removeAds(Integer id) {
        adsRepository.deleteById(id);
    }

    @Override
    public FullAdsDto getAds(Integer id) {
        Optional<Ads> ads = adsRepository.findById(id);
        if(ads.isPresent()) {
            return adsMapper.toFullAdsDto(ads.get());
        }
        return null;
    }

    @Override
    public AdsDto updateAds(Integer id, AdsDto ads) {
        if(adsRepository.existsById(id)) {
            adsRepository.save(adsMapper.toAds(ads));
            return ads;
        }
        return null;
    }

}
