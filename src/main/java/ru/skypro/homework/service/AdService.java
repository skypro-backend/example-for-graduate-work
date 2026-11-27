package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Ads getAllAds() {
        List<Ad> adsList = adMapper.toDtoList(adRepository.findAll());
        Ads ads = new Ads();
        ads.setCount(adsList.size());
        ads.setResults(adsList);
        return ads;
    }

    @Transactional
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication) {
        UserEntity author = userService.getUserEntityByEmail(authentication.getName());
        AdEntity ad = adMapper.toEntity(properties);
        ad.setAuthor(author);
        // Сохранение картинки будет добавлено на Этапе IV
        AdEntity savedAd = adRepository.save(ad);
        return adMapper.toDto(savedAd);
    }

    @Transactional(readOnly = true)
    public ExtendedAd getAdById(Integer id) {
        AdEntity ad = getAdEntityById(id);
        return adMapper.toExtendedDto(ad);
    }

    @Transactional
    public void removeAd(Integer id, Authentication authentication) {
        AdEntity ad = getAdEntityById(id);
        checkAccess(ad, authentication);
        adRepository.delete(ad);
    }

    @Transactional
    public Ad updateAd(Integer id, CreateOrUpdateAd dto, Authentication authentication) {
        AdEntity ad = getAdEntityById(id);
        checkAccess(ad, authentication);
        adMapper.updateEntityFromDto(dto, ad);
        return adMapper.toDto(adRepository.save(ad));
    }

    @Transactional(readOnly = true)
    public Ads getMyAds(Authentication authentication) {
        UserEntity user = userService.getUserEntityByEmail(authentication.getName());
        List<Ad> adsList = adMapper.toDtoList(adRepository.findAllByAuthorId(user.getId()));
        Ads ads = new Ads();
        ads.setCount(adsList.size());
        ads.setResults(adsList);
        return ads;
    }

    @Transactional
    public byte[] updateImage(Integer id, MultipartFile image, Authentication authentication) {
        AdEntity ad = getAdEntityById(id);
        checkAccess(ad, authentication);
        return new byte[0];
    }

    public AdEntity getAdEntityById(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Объявление не найдено: " + id));
    }

    private void checkAccess(AdEntity ad, Authentication authentication) {
        UserEntity currentUser = userService.getUserEntityByEmail(authentication.getName());
        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isAuthor = ad.getAuthor().equals(currentUser);
        if (!isAdmin && !isAuthor) {
            throw new AccessDeniedException("Недостаточно прав для изменения или удаления объявления");
        }
    }
}
