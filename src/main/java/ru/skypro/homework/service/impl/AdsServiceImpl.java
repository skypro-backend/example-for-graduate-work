package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.entity.Images;
import ru.skypro.homework.models.entity.User;
import ru.skypro.homework.models.mappers.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);
    private final AdsRepository adsRepository;
    private final ImageService imageService;
    private final AdsMapper adsMapper;

    @Override
    public List<AdsDto> getALLAds() {
        logger.info("Trying to get all ads");
        return adsRepository.findAll()
                .stream()
                .map(adsMapper::toAdsDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdsDto addAds(CreateAdsDto ads, MultipartFile file) throws IOException {
        logger.info("Trying to add new ad");
        Images images = imageService.addImage(file);
        Ads newAds = adsMapper.fromCreateAds(ads, new User(), images);
        Ads response = adsRepository.save(newAds);
        logger.info("The ad with pk = {} was saved ", response.getPk());
        return adsMapper.toAdsDto(response);
    }

    @Override
    public List<AdsDto> getAdsMe(Boolean authenticated, String authority, Object credentials, Object details, Object principal) {
        // FIXME: Just returns all

        logger.info("Trying to get all user's ads");
        return adsRepository.findAll()
                .stream()
                .map(adsMapper::toAdsDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAds(Integer id) {
        logger.info("Trying to remove the ad with id = {}", id);
        checkOnExistingAds(id);
        adsRepository.deleteById(id);
        logger.info("The ad with id = {} was removed", id);
    }

    @Override
    public FullAdsDto getAds(Integer id) {
        logger.info("Trying to get the ad with id = {}", id);
        checkOnExistingAds(id);
        Ads ads = adsRepository.findById(id).orElseThrow();
        logger.info("The ad with id = {} was found", id);
        return adsMapper.toFullAdsDto(ads);
    }

    @Override
    public AdsDto updateAds(Integer id, AdsDto ads) {
        logger.info("Trying to update the ad with id = {}", id);
        checkOnExistingAds(id);
        adsRepository.save(adsMapper.toAds(ads)); // FIXME: it seems won't work
        logger.info("The ad with id = {} was updated ", id);
        return ads;
    }

    //  FIXME: The same method as in AdsCommentsServiceImpl
    private void checkOnExistingAds(Integer ad_pk) {
        if (adsRepository.findById(ad_pk).isEmpty()) {
            logger.warn("The ad with id = {} does not exist", ad_pk);
            throw new NotFoundException("Ad with id = " + ad_pk + " does not exist");
        }
    }
}
