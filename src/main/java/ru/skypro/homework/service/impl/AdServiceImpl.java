package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Override
    public Ads getAllAds() {
        List<Ad> adList = adRepository.findAll().stream()
                .map(adMapper::toAdDto)
                .collect(Collectors.toList());
        Ads ads = new Ads();
        ads.setCount(adList.size());
        ads.setResults(adList);
        return ads;
    }

    @Override
    public Ad addAd(CreateOrUpdateAd dto, MultipartFile image, String username) {
        UserEntity author = userRepository.findByEmail(username).orElseThrow();
        AdEntity adEntity = adMapper.toEntity(dto);
        adEntity.setAuthor(author);
        adEntity.setImage(image.getOriginalFilename()); // Упрощенно
        return adMapper.toAdDto(adRepository.save(adEntity));
    }

    @Override
    public ExtendedAd getAd(Integer id) {
        return adRepository.findById(id)
                .map(adMapper::toExtendedAdDto)
                .orElseThrow();
    }

    @Override
    public void removeAd(Integer id) {
        adRepository.deleteById(id);
    }

    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd dto) {
        AdEntity ad = adRepository.findById(id).orElseThrow();
        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setDescription(dto.getDescription());
        return adMapper.toAdDto(adRepository.save(ad));
    }

    @Override
    public Ads getAdsMe(String username) {
        UserEntity author = userRepository.findByEmail(username).orElseThrow();
        List<Ad> adList = adRepository.findAllByAuthorId(author.getId()).stream()
                .map(adMapper::toAdDto)
                .collect(Collectors.toList());
        Ads ads = new Ads();
        ads.setCount(adList.size());
        ads.setResults(adList);
        return ads;
    }

    @Override
    public void updateAdImage(Integer id, MultipartFile image) {
        AdEntity ad = adRepository.findById(id).orElseThrow();
        ad.setImage(image.getOriginalFilename());
        adRepository.save(ad);
    }

    @Override
    public boolean isAuthorOrAdmin(Integer id, String username) {
        AdEntity ad = adRepository.findById(id).orElseThrow();
        return ad.getAuthor().getEmail().equals(username) ||
                userRepository.findByEmail(username).get().getRole() == Role.ADMIN;
    }


}