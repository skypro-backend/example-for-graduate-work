package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdIsNotFoundException;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final ImageServiceImpl imageService;

    @Override
    public Ad addAd(MultipartFile image, CreateOrUpdateAd adDetails, UserDetails userDetails) {

        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        AdEntity adEntity = adMapper.createOrUpdateAdDTOToAd(adDetails);
        ImageEntity imageEntity = imageService.uploadImage(image);

        adEntity.setAuthor(userEntity);
        adEntity.setImageEntity(imageEntity);
        adRepository.save(adEntity);
        return adMapper.adToAdDTO(adEntity);
    }

    @Override
    public Ads getAllAds() {
        return new Ads(adRepository.findAll().stream()
                .map(adMapper::adToAdDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public Ads getAdsByCurrentUser(UserDetails userDetails) {
        return new Ads(adRepository.findAdsByEmail(userDetails.getUsername()).stream()
                .map(adMapper::adToAdDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public ExtendedAd getFullAd(int id) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        return adMapper.adEntityToExtendedAdDTO(adEntity);
    }

    @Override
    public Ad updateAd(int id, CreateOrUpdateAd adDetails, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        checkAccess(userDetails, adEntity);
        adEntity.setTitle(adDetails.getTitle());
        adEntity.setPrice(adDetails.getPrice());
        adEntity.setDescription(adDetails.getDescription());

        adRepository.save(adEntity);

        return adMapper.adToAdDTO(adEntity);
    }

    @Override
    public void removeAd(int id, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        checkAccess(userDetails, adEntity);
        adRepository.deleteById(id);
    }

    @Override
    public void updateImage(int id, MultipartFile image, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        checkAccess(userDetails, adEntity);
        ImageEntity imageEntity = imageService.uploadImage(image);
        imageService.deleteImage(adEntity);
        adEntity.setImageEntity(imageEntity);

        adRepository.save(adEntity);
    }
    private void checkAccess(UserDetails userDetails, AdEntity adEntity) {
        if (!userDetails.getUsername().equals(adEntity.getAuthor().getEmail())
                && !userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new ForbiddenException();
        }
    }

}

