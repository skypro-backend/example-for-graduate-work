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
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final ImageServiceImpl imageService;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;

    @Override
    public Ad addAd(MultipartFile image, CreateOrUpdateAd adDetails, UserDetails userDetails) {

        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        AdEntity adEntity = adMapper.createOrUpdateAdDTOToAd(adDetails);
        ImageEntity imageEntity = imageService.uploadImage(image);

        adEntity.setAuthor(userEntity);
        adEntity.setImageEntity(imageEntity);

        imageRepository.save(imageEntity);
        adRepository.save(adEntity);

        return adMapper.adToAdDTO(adEntity);
    }

    @Override
    public Ads getAllAds() {
        List<AdEntity> adEntities = adRepository.findAll();
        List<Ad> listAds = adMapper.listAdEntityToListAdDTO(adEntities);
        Ads ads = new Ads();
        ads.setCount(listAds.size());
        ads.setResults(listAds);

        return ads;
    }

    @Override
    public Ads getAdsByCurrentUser(UserDetails userDetails) {
        List<AdEntity> adEntities = adRepository.findAdsByAuthor_id(userDetails.getUsername());
        List<Ad> listAds = adMapper.listAdEntityToListAdDTO(adEntities);
        Ads ads = new Ads();
        ads.setCount(listAds.size());
        ads.setResults(listAds);

        return ads;
    }

    @Override
    public ExtendedAd getFullAd(int id) {
        Optional<AdEntity> adEntity = adRepository.findById(id);
        if (adEntity.isPresent()) {
            return adMapper.adEntityToExtendedAdDTO(adEntity.get());
        } else throw new AdIsNotFoundException("Ad is not found");
    }

    @Override
    public Ad updateAd(int id, CreateOrUpdateAd adDetails, UserDetails userDetails) {
        Optional<AdEntity> adEntityOptional = adRepository.findById(id);
        if (adEntityOptional.isPresent()) {
            AdEntity adEntity = adEntityOptional.get();
            if (checkAccess(userDetails, adEntity)) {
                adEntity.setTitle(adDetails.getTitle());
                adEntity.setPrice(adDetails.getPrice());
                adEntity.setDescription(adDetails.getDescription());

                adRepository.save(adEntity);

                return adMapper.adToAdDTO(adEntity);

            } else throw new ForbiddenException();
        } else throw new AdIsNotFoundException("Ad is not found");
    }
//TODO: delete comments first, deleteById doesn't work, каскадное удаление
    @Override
    public void removeAd(int id, UserDetails userDetails) {
        Optional<AdEntity> adEntityOptional = adRepository.findById(id);
        if (adEntityOptional.isPresent()) {
            if (checkAccess(userDetails, adEntityOptional.get())) {
                adRepository.deleteById(id);
            } else throw new ForbiddenException();
        } else throw new AdIsNotFoundException("Ad is not found");
    }

    @Override
    public void updateImage(int id, MultipartFile image, UserDetails userDetails) {
        Optional<AdEntity> adEntityOptional = adRepository.findById(id);
        if (adEntityOptional.isPresent()) {
            AdEntity adEntity = adEntityOptional.get();
            if (checkAccess(userDetails, adEntity)) {
                ImageEntity imageEntity = imageService.uploadImage(image);

                adEntity.setImageEntity(imageEntity);

                adRepository.save(adEntity);
            } else throw new ForbiddenException();
        } else throw new AdIsNotFoundException("Ad is not found");
    }


    private boolean checkAccess(UserDetails userDetails, AdEntity adEntity) {
        return userDetails.getUsername().equals(adEntity.getAuthor().getEmail())
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}

