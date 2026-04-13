package ru.skypro.homework.service.impl;

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
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public Ads getAllAds() {
        log.debug("Getting all ads");
        var ads = adRepository.findAll();
        return adMapper.toAdsDto(ads);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExtendedAd> getAdById(Integer id) {
        log.debug("Getting ad by id: {}", id);
        return adRepository.findById(id)
                .map(adMapper::toExtendedDto);
    }

    @Override
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

    @Override
    @Transactional
    public Optional<Ad> updateAd(Integer id, CreateOrUpdateAd updateAd) {
        log.info("Updating ad with id: {}", id);

        AdEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + id));

        adMapper.updateAdFromDto(updateAd, ad);

        AdEntity updated = adRepository.save(ad);
        return Optional.of(adMapper.toDto(updated));
    }

    @Override
    @Transactional
    public boolean deleteAd(Integer id) {
        log.info("Deleting ad with id: {}", id);

        if (!adRepository.existsById(id)) {
            log.warn("Ad not found with id: {}", id);
            return false;
        }

        commentRepository.deleteByAdId(id);

        adRepository.findById(id).ifPresent(ad -> {
            if (ad.getImage() != null && !ad.getImage().isEmpty()) {
                imageService.deleteImage(ad.getImage());
            }
        });

        adRepository.deleteById(id);

        adRepository.flush();

        log.info("Ad deleted successfully with id: {}", id);
        return true;
    }

    @Override
    @Transactional
    public String updateAdImage(Integer adId, MultipartFile image) {
        log.info("Updating image for ad: {}", adId);

        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + adId));

        if (ad.getImage() != null && !ad.getImage().isEmpty()) {
            imageService.deleteImage(ad.getImage());
        }

        String imagePath = imageService.saveAdImage(image, adId);

        ad.setImage(imagePath);
        adRepository.save(ad);

        log.info("Ad image updated successfully for ad: {}", adId);
        return imagePath;
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getAdImage(Integer adId) {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + adId));

        return imageService.getImage(ad.getImage());
    }

    @Override
    @Transactional(readOnly = true)
    public Ads getAdsByUserEmail(String email) {
        log.debug("Getting ads for user: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        var ads = adRepository.findByAuthorId(user.getId());
        return adMapper.toAdsDto(ads);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isAdOwner(Integer adId, String email) {
        return adRepository.findById(adId)
                .map(ad -> ad.getAuthor().getEmail().equals(email))
                .orElse(false);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isAdmin(String email) {
        return userRepository.findByEmail(email)
                .map(user -> "ADMIN".equals(user.getRole()))
                .orElse(false);
    }
}