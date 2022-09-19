package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.entity.Images;
import ru.skypro.homework.models.mappers.AdsMapper;
import ru.skypro.homework.models.mappers.CommentsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final ImageService imageService;

    private final AdsMapper adsMapper = AdsMapper.INSTANCE;

    @Override
    public List<AdsDto> getALLAds() {
        return adsRepository.findAll()
                .stream()
                .map(e -> adsMapper.toAdsDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public AdsDto addAds(CreateAdsDto ads, MultipartFile file) throws IOException {
        Ads newAds = adsMapper.fromCreateAdsToAds(ads);
        Images images = imageService.addImage(file);
        newAds.setImage(images.getPk());
        newAds.setAuthor(1); //FIXME: should be a real author id!!
        return adsMapper.toAdsDto(adsRepository.save(newAds));
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
