package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    private final String IMAGE_DIRECTORY = "images/ads/";

    @Transactional(readOnly = true)
    public Ads getAllAds() {
        log.debug("Getting all ads");

        var ads = adRepository.findAll();
        return adMapper.toAdsDto(ads);
    }

    @Transactional(readOnly = true)
    public Optional<ExtendedAd> getAdById(Integer id) {
        log.debug("Getting ad by id: {}", id);

        return adRepository.findById(id)
                .map(adMapper::toExtendedDto);
    }

    @Transactional
    public Optional<Ad> createAd(CreateOrUpdateAd createAd, String userEmail) {
        log.info("Creating new ad by user: {}", userEmail);

        UserEntity author = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userEmail));

        AdEntity ad = adMapper.toEntity(createAd);
        ad.setAuthor(author);

        AdEntity saved = adRepository.save(ad);
        log.info("Ad created successfully with id: {}", saved.getId());

        return Optional.of(adMapper.toDto(saved));
    }

    @Transactional
    public Optional<Ad> updateAd(Integer id, CreateOrUpdateAd updateAd) {
        log.info("Updating ad with id: {}", id);

        AdEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + id));

        adMapper.updateAdFromDto(updateAd, ad);

        AdEntity updated = adRepository.save(ad);
        return Optional.of(adMapper.toDto(updated));
    }

    @Transactional
    public boolean deleteAd(Integer id) {
        log.info("Deleting ad with id: {}", id);

        if (!adRepository.existsById(id)) {
            log.warn("Ad not found with id: {}", id);
            return false;
        }

        adRepository.deleteById(id);
        log.info("Ad deleted successfully with id: {}", id);
        return true;
    }

    @Transactional(readOnly = true)
    public Ads getAdsByUserEmail(String email) {
        log.debug("Getting ads for user: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + email));

        var ads = adRepository.findByAuthorId(user.getId());
        return adMapper.toAdsDto(ads);
    }

    @Transactional
    public String updateAdImage(Integer adId, MultipartFile image) {
        log.info("Updating image for ad: {}", adId);

        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Айди объявления не найдено: " + adId));

        try {
            Path uploadPath = Paths.get(IMAGE_DIRECTORY);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, image.getBytes());

            String imageUrl = "/" + IMAGE_DIRECTORY + fileName;
            ad.setImage(imageUrl);
            adRepository.save(ad);

            log.info("Фотография объявления обновлена успешно: {}", adId);
            return imageUrl;

        } catch (IOException e) {
            log.error("Не удалось сохранить картинку объявления: {}", adId, e);
            throw new RuntimeException("Failed to save image", e);
        }
    }

    @Transactional(readOnly = true)
    public boolean isAdOwner(Integer adId, String email) {
        log.debug("Checking if user {} is owner of ad {}", email, adId);

        return adRepository.findById(adId)
                .map(ad -> ad.getAuthor().getEmail().equals(email))
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public boolean isAdmin(String email) {
        return userRepository.findByEmail(email)
                .map(user -> "ADMIN".equals(user.getRole()))
                .orElse(false);
    }
}