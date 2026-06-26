package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import java.util.List;

/**
 * Реализация сервиса для управления объявлениями {@link ru.skypro.homework.service.AdService}.
 * Содержит бизнес-логику взаимодействия с репозиториями объявлений и пользователей,
 * а также логику сохранения бинарных данных изображений в базу данных PostgreSQL.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    /** {@inheritDoc} */
    @Override
    @Transactional(readOnly = true)
    public Ads getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return adMapper.toAds(ads);
    }

    /** {@inheritDoc} */
    @Override
    public AdDto addAd(CreateOrUpdateAd properties, MultipartFile image, String username) {
        User author = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Ad ad = adMapper.toEntity(properties);
        ad.setAuthor(author);

        // Сначала сохраняем, чтобы получить сгенерированный ID
        Ad savedAd = adRepository.save(ad);

        try {
            savedAd.setImageData(image.getBytes());
            savedAd.setImage("/images/ad/" + savedAd.getId());
            adRepository.save(savedAd);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to save ad image");
        }

        log.info("Ad created with id: {} by user: {}", savedAd.getId(), username);
        return adMapper.toAdDto(savedAd);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional(readOnly = true)
    public ExtendedAd getAd(Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        return adMapper.toExtendedAd(ad);
    }

    /** {@inheritDoc} */
    @Override
    public void removeAd(Integer id) {
        adRepository.deleteById(id);
        log.info("Ad deleted with id: {}", id);
    }

    /** {@inheritDoc} */
    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAd properties) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        adMapper.updateAdFromDto(properties, ad);
        Ad savedAd = adRepository.save(ad);
        log.info("Ad updated with id: {}", id);
        return adMapper.toAdDto(savedAd);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional(readOnly = true)
    public Ads getAdsMe(String username) {
        User author = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Ad> ads = adRepository.findAllByAuthorId(author.getId());
        return adMapper.toAds(ads);
    }


    /** {@inheritDoc} */
    @Override
    public void updateAdImage(Integer id, MultipartFile image) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        try {
            ad.setImageData(image.getBytes());
            ad.setImage("/images/ad/" + id);
            adRepository.save(ad);
            log.info("Image updated for ad id: {}", id);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to update ad image");
        }
    }

    /**
     * Проверяет, является ли текущий авторизованный пользователь автором объявления
     * или он обладает правами администратора (ROLE_ADMIN).
     * <p>
     * Данный метод используется в аннотациях {@code @PreAuthorize} контроллера
     * для динамической проверки прав доступа перед вызовом методов модификации данных.
     *
     * @param adId уникальный идентификатор проверяемого объявления
     * @return {@code true}, если доступ разрешен (пользователь — автор или админ), иначе {@code false}
     */
    @Transactional(readOnly = true)
    public boolean isAuthorOrAdmin(Integer adId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Ad ad = adRepository.findById(adId).orElse(null);
        if (ad == null) return false;

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return isAdmin || ad.getAuthor().getEmail().equals(currentUsername);
    }
}