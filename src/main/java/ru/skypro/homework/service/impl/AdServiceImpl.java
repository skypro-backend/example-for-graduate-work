package ru.skypro.homework.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.ArrayList;
import java.util.List;

import static ru.skypro.homework.dto.Role.ADMIN;


@Service
public class AdServiceImpl implements AdService {

    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;

    public AdServiceImpl(UserRepository userRepository, AdRepository adRepository, AdMapper adMapper, ImageService imageService) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
    }

    @Override
    public Ad createdAd(CreateOrUpdateAd dto, MultipartFile image, Authentication authentication) {
        AdEntity adEntity = adMapper.toAd(dto);
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        adEntity.setAuthor(userEntity);
        String imageName = imageService.saveImage(image);
        adEntity.setImage(imageName);
        AdEntity savedAd = adRepository.save(adEntity);
        return adMapper.toAdDto(savedAd);

    }

    @Override
    public Ads getAllAds() {
        List<AdEntity> adsEntities = adRepository.findAll();
        List<Ad> adsDto = new ArrayList<>();
        for (AdEntity entity : adsEntities) {
            adsDto.add(adMapper.toAdDto(entity));
        }
        int count = adsDto.size();
        return new Ads(count, adsDto);

    }

    @Override
    public ExtendedAd getAdById(Long id) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление с id: " + id + " не найдено"));
        return adMapper.toExtendedAd(adEntity);


    }

    public Ads getAdsMe(Authentication authentication) {
        String email = authentication.getName();
        UserEntity me = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь с email: " + email + " не найден"));
        Long authorId = me.getId();
        List<AdEntity> adEntityList = adRepository.findByAuthorId(authorId);
        List<Ad> adsDtoList = new ArrayList<>();
        for (AdEntity entity : adEntityList) {
            adsDtoList.add(adMapper.toAdDto(entity));
        }
        int count = adsDtoList.size();
        return new Ads(count, adsDtoList);

    }

    @Override
    public Ad updateAd(Long id, CreateOrUpdateAd dto, Authentication authentication) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление с id: " + id + " не найдено"));
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        boolean isAuthor = adEntity.getAuthor().getId().equals(userEntity.getId());
        boolean isAdmin = userEntity.getRole() == ADMIN;
        AdEntity savedAd;
        if (isAuthor || isAdmin) {
            adMapper.toUpdateAdFromDto(adEntity, dto);
            savedAd = adRepository.save(adEntity);
        } else {
            throw new AccessDeniedException("Недостаточно прав для изменения этого объявления");
        }
        return adMapper.toAdDto(savedAd);
    }

    @Override
    public void deleteAd(Long id, Authentication authentication) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление с id: " + id + " не найдено"));
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        boolean isAuthor = adEntity.getAuthor().getId().equals(userEntity.getId());
        boolean isAdmin = userEntity.getRole() == ADMIN;
        if (isAuthor || isAdmin) {
            adRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("Недостаточно прав для удаления этого объявления");
        }
    }

    @Override
    public void updateAdImage(Long id, MultipartFile image, Authentication authentication) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление с id: " + id + " не найдено"));
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        boolean isAuthor = adEntity.getAuthor().getId().equals(userEntity.getId());
        boolean isAdmin = userEntity.getRole() == ADMIN;
        if (isAuthor || isAdmin) {
            if (adEntity.getImage() != null && !adEntity.getImage().isBlank()) {
                imageService.deleteImage(adEntity.getImage());
            }
            String newImageName = imageService.saveImage(image);
            adEntity.setImage(newImageName);
            AdEntity updatedAdEntity = adRepository.save(adEntity);
            adMapper.toAdDto(updatedAdEntity);
        } else {
            throw new AccessDeniedException("Недостаточно прав для удаления этого объявления");
        }
    }

    /**
     * Метод-обёртка для контроллера
     * вызывает updateAdImage(Long id, MultipartFile image, Authentication authentication),
     * а потом загружает и возвращает картинку
     */
    @Override
    public Resource updateAdImageAndReturn(Long id, MultipartFile image, Authentication authentication) {
        updateAdImage(id, image, authentication);
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));
        return imageService.getImage(adEntity.getImage());

    }

}
