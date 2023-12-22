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

/**
 * Service for addition, receipt, update and removal of an ad
 */
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final ImageServiceImpl imageService;

    /**
     * Adds an ad by an authorized user
     * @param image contains image URL
     * @param adDetails is CreateOrUpdateAd DTO consisting of title, price and description of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Ad DTO consisting of author id, image url, ad id, price and title
     */
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

    /**
     * Gets ads of all users
     * @return Ads DTO consisting of a list of ads and the size of the list
     */
    @Override
    public Ads getAllAds() {
        return new Ads(adRepository.findAll().stream()
                .map(adMapper::adToAdDTO)
                .collect(Collectors.toList()));
    }

    /**
     * Gets all ads of an authorized user
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Ads DTO consisting of a list of ads and the size of the list
     */
    @Override
    public Ads getAdsByCurrentUser(UserDetails userDetails) {
        return new Ads(adRepository.findAdsByEmail(userDetails.getUsername()).stream()
                .map(adMapper::adToAdDTO)
                .collect(Collectors.toList()));
    }

    /**
     * Gets full info about ad by id
     * @param id of an ad
     * @return ExtendedAd DTO consisting of ad id, author firstname, author lastname, description, email, image, phone, price and title
     */
    @Override
    public ExtendedAd getFullAd(int id) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        return adMapper.adEntityToExtendedAdDTO(adEntity);
    }

    /**
     * Updates ad info
     * @param id of an ad
     * @param adDetails is CreateOrUpdateAd DTO consisting of title, price and description of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Ad DTO consisting of author id, image url, ad id, price and title
     */
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

    /**
     * Removes an ad
     * @param id of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     */
    @Override
    public void removeAd(int id, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        checkAccess(userDetails, adEntity);
        adRepository.deleteById(id);
    }

    /**
     * Updates ad image
     * @param id of an ad
     * @param image contains image URL
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     */
    @Override
    public void updateImage(int id, MultipartFile image, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        checkAccess(userDetails, adEntity);
        ImageEntity imageEntity = imageService.uploadImage(image);
        imageService.deleteImage(adEntity);
        adEntity.setImageEntity(imageEntity);

        adRepository.save(adEntity);
    }

    /**
     * Throws ForbiddenException if username from userDetails is not equal to email from adEntity and the authority from userDetails is not admin
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @param adEntity contains ad id, price, title, description, UserEntity, ImageEntity and a list of CommentEntities
     */
    private void checkAccess(UserDetails userDetails, AdEntity adEntity) {
        if (!userDetails.getUsername().equals(adEntity.getAuthor().getEmail())
                && !userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new ForbiddenException("Access is not allowed");
        }
    }

}

