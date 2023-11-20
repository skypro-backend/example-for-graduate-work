package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;

    private final UserService userService;

    public AdServiceImpl(AdRepository adRepository, AdMapper adMapper, UserService userService) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.userService = userService;
    }

    @Override
    public Ads getAllAds() {
        List<Ad> dtos = adRepository.findAll().stream()
                .map(entity -> adMapper.mapToAdDto(entity))
                .collect(Collectors.toList());
        return new Ads(dtos.size(), dtos);
    }

    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image) {
        // надо подумать как обрабатывать фото
        return null;
    }

    @Override
    public ExtendedAd getAds(Integer id) {
        AdEntity entity = adRepository.findById(id).get();
        return adMapper.mapToExtendedAdDto(entity);
    }

    @Override
    public boolean removeAd(Integer id) {
        AdEntity ad = adRepository.findById(id).get();
        if (ad != null) {
            adRepository.delete(ad);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Ad updateAds(Integer id, CreateOrUpdateAd dto) {
        AdEntity entity = adRepository.findById(id).get();

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        adRepository.save(entity);
        return adMapper.mapToAdDto(entity);

    }

    @Override
    public Ads getAdsMe(String username) {
        UserEntity author = userService.checkUserByUsername(username);

        List<Ad> ads = adRepository.findByAuthor(author).stream()
                .map(ad -> adMapper.mapToAdDto(ad))
                .collect(Collectors.toList());

        return new Ads(ads.size(), ads);
    }

    @Override
    public MultipartFile updateImage(Integer id, MultipartFile image) {
        return null; // todo прописать логику сервиса
    }

}
