package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Override
    public Ads getAllAds() {
        List<AdEntity> adEntities = adRepository.findAll();
        Ads ads = new Ads();
        ads.setCount((long) adEntities.size());
        ads.setResults(adMapper.toAdDtoList(adEntities));
        return ads;
    }

    @Transactional
    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication) {
        UserEntity author = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        AdEntity adEntity = adMapper.toEntity(properties);
        adEntity.setAuthor(author);
        adEntity.setImage(image.getOriginalFilename());

        adRepository.save(adEntity);
        return adMapper.toDto(adEntity);
    }

    @Override
    public ExtendedAd getAds(Long id) {
        AdEntity ad = adRepository.findById(id) // Убрали Long.valueOf
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ad not found"));
        return adMapper.toExtendedDto(ad);
    }

    @Transactional
    @Override
    public void removeAd(Long id, Authentication authentication) {
        AdEntity ad = adRepository.findById(id) // Убрали Long.valueOf
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ad not found"));

        checkPermission(ad, authentication);
        adRepository.delete(ad);
    }

    @Transactional
    @Override
    public Ad updateAds(Long id, CreateOrUpdateAd properties, Authentication authentication) {
        AdEntity ad = adRepository.findById(id) // Убрали Long.valueOf
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ad not found"));

        checkPermission(ad, authentication);

        ad.setTitle(properties.getTitle());
        ad.setPrice(properties.getPrice());
        ad.setDescription(properties.getDescription());

        adRepository.save(ad);
        return adMapper.toDto(ad);
    }

    @Override
    public Ads getAdsMe(Authentication authentication) {
        List<AdEntity> myAds = adRepository.findAllByAuthorEmail(authentication.getName());
        Ads ads = new Ads();
        ads.setCount((long) myAds.size());
        ads.setResults(adMapper.toAdDtoList(myAds));
        return ads;
    }

    @Transactional
    @Override
    public byte[] updateImage(Long id, MultipartFile image, Authentication authentication) {
        AdEntity ad = adRepository.findById(id) // Убрали Long.valueOf
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ad not found"));

        checkPermission(ad, authentication);

        try {
            ad.setImage(image.getOriginalFilename());
            adRepository.save(ad);
            return image.getBytes();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving image");
        }
    }

    private void checkPermission(AdEntity ad, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isOwner = ad.getAuthor().getEmail().equals(authentication.getName());

        if (!isAdmin && !isOwner) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No permission");
        }
    }
}