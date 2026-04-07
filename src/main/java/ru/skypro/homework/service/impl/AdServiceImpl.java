package ru.skypro.homework.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdvertisementRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private final AdvertisementRepository adRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;
    private final String uploadDir = "uploads/images/";
    public AdServiceImpl(AdvertisementRepository adRepository, AdMapper adMapper, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.userRepository = userRepository;
        new File(uploadDir).mkdirs();
    }

    @Override
    public List<Ad> getAllAds() {
        List<AdvertisementEntity> ads = adRepository.findAll();
        return ads.stream()
                .map(adMapper::toDto)
                .toList();
    }
    @Override
    public Ad createAd(CreateOrUpdateAd adDto, MultipartFile image, String authorEmail) {
        AdvertisementEntity entity = adMapper.toEntity(adDto);

        if (image != null && !image.isEmpty()) {
            try {
                // Создаем уникальное имя файла
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path filepath = Paths.get(uploadDir, filename);
                Files.copy(image.getInputStream(), filepath);
                // Указываем путь к изображению для клиента
                entity.setImage("/images/" + filename);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка при сохранении изображения", e);
            }
        }

        UserEntity author = userRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        entity.setAuthor(author);

        AdvertisementEntity saved = adRepository.save(entity);
        return adMapper.toDto(saved);
    }
    @Override
    public Ad getAdById(Long id) {
        AdvertisementEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));
        return adMapper.toDto(ad);
    }
    /**
     * Удаление объявления.
     * Используем @PreAuthorize для проверки прав.
     * Пользователь может удалить только свою объяву или если имеет роль ROLE_ADMIN.
     */
    @PreAuthorize("@adSecurity.hasAccess(#id)")
    @Override
    public void deleteAd(Long id) {
        AdvertisementEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        adRepository.delete(ad);
    }


    /**
     * Обновление объявления.
     * Плюс проверка доступа с помощью @PreAuthorize.
     */
    @PreAuthorize("@adSecurity.hasAccess(#id)")
    @Override
    public AdvertisementEntity updateAd(Long id, CreateOrUpdateAd request) {
        AdvertisementEntity existingAd = adRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));

        if (request.getTitle() != null) existingAd.setTitle(request.getTitle());
        if (request.getPrice() != null) existingAd.setPrice(request.getPrice());
        if (request.getDescription() != null) existingAd.setDescription(request.getDescription());

        return adRepository.save(existingAd);
    }


    public List<AdvertisementEntity> getMyAds(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return adRepository.findAllByAuthor(user);
    }
    /**
     * Обновление изображения объявления.
     * Также с проверкой доступа через @PreAuthorize.
     */
    @PreAuthorize("@adSecurity.hasAccess(#id)")
    @Override
    public AdvertisementEntity updateAdImage(Long id, MultipartFile file) {
        AdvertisementEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));

        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Файл пустой");
        }

        try {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filepath = Paths.get(uploadDir, filename);
            Files.copy(file.getInputStream(), filepath);
            ad.setImage("/images/" + filename);
            return adRepository.save(ad);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении изображения", e);
        }
    }
    /**
     * Проверка: является ли пользователь автором объявления.
     * Можно использовать внутри методов или через @PreAuthorize.
     */
    public boolean isAuthor(Long adId, String username) {
        AdvertisementEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));
        return ad.getAuthor().getEmail().equals(username);
    }
}