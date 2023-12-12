package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;


import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final ImageServiceImpl imageService;
    private final ImageRepository imageRepository;

    public Ad addAd(MultipartFile image, CreateOrUpdateAd adDetails, UserDetails userDetails)
            throws IOException {

        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        AdEntity adEntity = adMapper.createOrUpdateAdDTOToAd(adDetails);
        ImageEntity imageEntity = imageService.uploadAdImage(image);

        adEntity.setAuthor(userEntity);
        adEntity.setImageEntity(imageEntity);

        imageRepository.save(imageEntity);
        adRepository.save(adEntity);

        return adMapper.adToAdDTO(adEntity);
    }

    public Ads getAllAds() {
        List<AdEntity> adEntities = adRepository.findAll();
        List<Ad> listAds = adMapper.listAdEntityToListAdDTO(adEntities);
        Ads ads = new Ads();
        ads.setCount(listAds.size());
        ads.setResults(listAds);

        return ads;
    }


    public Ads getAdsByCurrentUser(UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        List<AdEntity> adEntities = userEntity.getAds();
        List<Ad> listAds = adMapper.listAdEntityToListAdDTO(adEntities);
        Ads ads = new Ads();
        ads.setCount(listAds.size());
        ads.setResults(listAds);

        return ads;
    }

    public ExtendedAd getFullAd(int id) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        return adMapper.adEntityToExtendedAdDTO(adEntity);
    }

    public Ad updateAd(int id, CreateOrUpdateAd adDetails, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        if (checkAccess(userDetails, adEntity)) {
            adEntity.setTitle(adDetails.getTitle());
            adEntity.setPrice(adDetails.getPrice());
            adEntity.setDescription(adDetails.getDescription());

            adRepository.save(adEntity);

            return adMapper.adToAdDTO(adEntity);

        } else throw new ForbiddenException();
    }

    public void removeAd(int id, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        if (checkAccess(userDetails, adEntity)) {
            adRepository.deleteById(id);
        } else throw new ForbiddenException();
    }

    public void updateImage(int id, MultipartFile image) throws IOException {
        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        ImageEntity imageEntity = imageService.uploadAdImage(image);

        adEntity.setImageEntity(imageEntity);

        adRepository.save(adEntity);
    }


    private boolean checkAccess(UserDetails userDetails, AdEntity adEntity) {
        return userDetails.getUsername().equals(adEntity.getAuthor().getEmail())
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

    }
}
