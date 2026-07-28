package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.utils.SecurityUtils;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageStorageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageStorageService imageStorageService;
    private final SecurityUtils securityUtils;

    @Override
    public Ads getAllAds() {
        List<Ad> ads = adRepository.findAll().stream()
                .map(adMapper::toDto)
                .toList();
        return new Ads(ads.size(), ads);
    }

    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication) {
        User author = securityUtils.getCurrentUser(authentication);
        ru.skypro.homework.entity.Ad ad = adMapper.toEntity(properties);
        ad.setAuthor(author);
        ru.skypro.homework.entity.Ad savedAd = adRepository.save(ad);
        savedAd.setImage(imageStorageService.save(image, "ad_" + savedAd.getId()));
        return adMapper.toDto(adRepository.save(savedAd));
    }

    @Override
    public Ads getAdsMe(Authentication authentication) {
        User author = securityUtils.getCurrentUser(authentication);
        List<Ad> ads = adRepository.findAllByAuthor(author).stream()
                .map(adMapper::toDto)
                .toList();
        return new Ads(ads.size(), ads);
    }

    @Override
    public ExtendedAd getAds(Integer id) {
        return adMapper.toExtendedDto(getAdOrThrow(id));
    }

    @Override
    public void removeAd(Integer id, Authentication authentication) {
        ru.skypro.homework.entity.Ad ad = getAdOrThrow(id);
        securityUtils.checkOwnership(ad.getAuthor(), authentication);
        adRepository.delete(ad);
    }

    @Override
    public Ad updateAds(Integer id, CreateOrUpdateAd dto, Authentication authentication) {
        ru.skypro.homework.entity.Ad ad = getAdOrThrow(id);
        securityUtils.checkOwnership(ad.getAuthor(), authentication);
        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setDescription(dto.getDescription());
        return adMapper.toDto(adRepository.save(ad));
    }

    @Override
    public byte[] updateImage(Integer id, MultipartFile image, Authentication authentication) {
        ru.skypro.homework.entity.Ad ad = getAdOrThrow(id);
        securityUtils.checkOwnership(ad.getAuthor(), authentication);
        String path = imageStorageService.save(image, "ad_" + ad.getId());
        ad.setImage(path);
        adRepository.save(ad);
        return imageStorageService.read(path);
    }

    private ru.skypro.homework.entity.Ad getAdOrThrow(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Объявление не найдено"));
    }
}
