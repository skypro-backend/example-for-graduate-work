package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdvertisementRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.List;

/**
 * Реализация сервиса для работы с объявлениями.
 * Включает операции получения, создания, обновления, удаления объявлений и работу с изображениями.
 * Обеспечивает основные бизнес-операции, связанные с объявлениями.
 */
@Slf4j
@Service
public class AdServiceImpl implements AdService {

    private final AdvertisementRepository adRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public AdServiceImpl(AdvertisementRepository adRepository,
                         AdMapper adMapper,
                         UserRepository userRepository,
                         ImageService imageService) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Override
    public Ads getAllAds() {
        List<Ad> ads = adRepository.findAll().stream()
                .map(adMapper::toDto)
                .toList();
        return Ads.builder()
                .count(ads.size())
                .results(ads)
                .build();
    }

    @Override
    public Ad createAd(CreateOrUpdateAd adDto, MultipartFile image, String email) {

        UserEntity author = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));

        AdvertisementEntity entity = adMapper.toEntity(adDto);

        if (image != null && !image.isEmpty()) {
            String imageUrl = imageService.saveImage(image);
            entity.setImage(imageUrl);
        }

        entity.setAuthor(author);

        AdvertisementEntity saved = adRepository.save(entity);
        log.info("Создано объявление пользователем: {}", email);
        return adMapper.toDto(saved);
    }

    @Override
    public ExtendedAd getAdById(Integer id) {
        AdvertisementEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));
        UserEntity author = ad.getAuthor();
        return ExtendedAd.builder()
                .pk(ad.getId())
                .authorFirstName(author != null ? author.getFirstName() : null)
                .authorLastName(author != null ? author.getLastName() : null)
                .description(ad.getDescription())
                .email(author != null ? author.getEmail() : null)
                .image(ad.getImage())
                .phone(author != null ? author.getPhone() : null)
                .price(ad.getPrice())
                .title(ad.getTitle())
                .build();
    }

    @PreAuthorize("@adSecurity.hasAccess(#id)")
    @Override
    public void deleteAd(Integer id, String email) {
        AdvertisementEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));

        if (ad.getImage() != null && !ad.getImage().isEmpty()) {
            imageService.deleteImage(ad.getImage());
        }

        adRepository.delete(ad);
        log.info("Удалено объявление {} пользователем {}", id, email);
    }

    @PreAuthorize("@adSecurity.hasAccess(#id)")
    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd request, String email) {

        AdvertisementEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));

        if (request.getTitle() != null) ad.setTitle(request.getTitle());
        if (request.getPrice() != null) ad.setPrice(request.getPrice());
        if (request.getDescription() != null) ad.setDescription(request.getDescription());

        AdvertisementEntity saved = adRepository.save(ad);
        log.info("Объявление {} обновлено пользователем {}", id, email);
        return adMapper.toDto(saved);
    }

    @Override
    public Ads getMyAds(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));

        List<Ad> ads = adRepository.findAllByAuthor(user).stream()
                .map(adMapper::toDto)
                .toList();
        return Ads.builder()
                .count(ads.size())
                .results(ads)
                .build();
    }

    @PreAuthorize("@adSecurity.hasAccess(#id)")
    @Override
    public byte[] updateAdImage(Integer id, MultipartFile file, String email) {

        AdvertisementEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));

        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Файл пустой");
        }

        if (ad.getImage() != null && !ad.getImage().isEmpty()) {
            imageService.deleteImage(ad.getImage());
        }

        String imageUrl = imageService.saveImage(file);
        ad.setImage(imageUrl);

        AdvertisementEntity saved = adRepository.save(ad);
        log.info("Обновлено изображение объявления {} пользователем {}", id, email);

        String imageName = saved.getImage().substring(saved.getImage().lastIndexOf("/") + 1);
        return imageService.getImage(imageName);
    }

    public boolean isAuthor(Integer adId, String email) {
        AdvertisementEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));
        return ad.getAuthor().getEmail().equals(email);
    }
}