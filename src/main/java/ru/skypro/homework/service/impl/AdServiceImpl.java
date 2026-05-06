package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.request.AdRequestDto;
import ru.skypro.homework.dto.response.AdResponseDto;
import ru.skypro.homework.dto.response.AdsResponseDto;
import ru.skypro.homework.dto.response.ExtendedAdResponseDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.service.CurrentUserService;
import ru.skypro.homework.service.FileStorageService;

import java.util.List;

/**
 * Сервис работы с объявлениями.
 * Отвечает за:
 * - получение списка объявлений,
 * - создание и редактирование объявлений,
 * - удаление объявлений,
 * - работу с изображениями.
 * Поддерживает проверку владельца объявления и роли ADMIN через Spring Security @PreAuthorize.
 */
@Service("adService")
@RequiredArgsConstructor
@Transactional
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final FileStorageService fileStorageService;
    private final CurrentUserService currentUserService;

    /**
     * Получение всех объявлений без авторизации.
     */
    @Override
    @Transactional(readOnly = true)
    public AdsResponseDto getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return adMapper.toAdsResponseDto(ads);
    }

    /**
     * Получение расширенной информации об объявлении по id.
     * @param id идентификатор объявления
     * @return расширенное DTO объявления
     */
    @Override
    @Transactional(readOnly = true)
    public ExtendedAdResponseDto getAd(Integer id) {
        Ad ad = getAdOrThrow(id);
        return adMapper.extendedAdToDto(ad);
    }

    private Ad getAdOrThrow(Integer id) {
        return adRepository.findById(id).orElseThrow(() -> new NotFoundException("Ad not found"));
    }

    /**
     * Получение объявлений текущего авторизованного пользователя.
     */
    @Override
    @Transactional(readOnly = true)
    public AdsResponseDto getMyAds() {
        User currentUser = currentUserService.getCurrentUser();
        List<Ad> ads = adRepository.findAllByAuthor(currentUser);
        return adMapper.toAdsResponseDto(ads);
    }

    /**
     * Создание нового объявления текущим пользователем.
     * @param properties данные объявления
     * @param image изображение объявления
     * @return созданное объявление
     */
    @Override
    public AdResponseDto addAd(AdRequestDto properties, MultipartFile image) {
        User currentUser = currentUserService.getCurrentUser();
        Ad ad = adMapper.adRequestDtoToAd(properties);
        ad.setAuthor(currentUser);
        adRepository.save(ad);
        String imagePath = fileStorageService.saveAdImage(ad.getId(), image);
        ad.setImage(imagePath);
        return adMapper.adToDto(ad);
    }

    /**
     * Обновление объявления.
     * Доступно только владельцу или администратору.
     * @param id идентификатор объявления
     */
    @PreAuthorize("hasRole('ADMIN') or @adService.isOwner(#id, authentication)")
    @Override
    public AdResponseDto updateAd(Integer id, AdRequestDto dto) {
        Ad ad = getAdOrThrow(id);
        adMapper.updateAdFromDto(dto, ad);
        adRepository.save(ad);
        return adMapper.adToDto(ad);
    }

    /**
     * Удаление объявления.
     * Доступно только владельцу или администратору.
     * @param id идентификатор объявления
     */
    @PreAuthorize("hasRole('ADMIN') or @adService.isOwner(#id, authentication)")
    @Override
    public void deleteAd(Integer id) {
        Ad ad = getAdOrThrow(id);
        adRepository.delete(ad);
    }

    /**
     * Обновление изображения.
     * Доступно только владельцу или администратору.
     * @param id идентификатор объявления
     */
    @PreAuthorize("hasRole('ADMIN') or @adService.isOwner(#id, authentication)")
    @Override
    public void updateImage(Integer id, MultipartFile image) {
        Ad ad = getAdOrThrow(id);
        String imagePath = fileStorageService.saveAdImage(ad.getId(), image);
        ad.setImage(imagePath);
        adRepository.save(ad);
    }

    /**
     * Проверяет, является ли пользователь владельцем объявления.
     * @param adId идентификатор объявления
     * @param authentication текущая аутентификация
     * @return true, если пользователь владелец
     */
    public boolean isOwner(Integer adId, Authentication authentication) {
        return adRepository.existsByIdAndAuthorUsername(adId, authentication.getName());
    }
}
