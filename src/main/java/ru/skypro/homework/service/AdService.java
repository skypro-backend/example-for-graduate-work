package ru.skypro.homework.service;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ad.Ad;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с объявлениями.
 * Обеспечивает создание, получение, обновление и удаление объявлений,
 * а также получение объявлений текущего пользователя.
 */
@Service
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    public AdService(AdRepository adRepository,
                     UserRepository userRepository,
                     AdMapper adMapper) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
    }

    /**
     * Получает список всех объявлений.
     *
     * @return объект Ads со списком объявлений и их количеством
     */
    public Ads getAllAds() {
        List<Ad> ads = adRepository.findAll()
                .stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());

        Ads result = new Ads();
        result.setResults(ads);
        result.setCount(ads.size());

        return result;
    }


    /**
     * Создаёт новое объявление.
     *
     * @param dto данные для создания объявления
     * @param username имя пользователя (автор объявления)
     * @return созданное объявление
     */
    public Ad createAd(CreateOrUpdateAd dto, String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AdEntity entity = adMapper.toEntity(dto);
        entity.setAuthor(user);

        return adMapper.toDto(adRepository.save(entity));
    }

    /**
     * Получает расширенную информацию об объявлении.
     *
     * @param id идентификатор объявления
     * @return расширенное объявление
     */
    public ExtendedAd getExtendedAd(Integer id) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        return adMapper.toExtendedDto(entity);
    }


    /**
     * Обновляет объявление.
     *
     * @param id идентификатор объявления
     * @param dto новые данные объявления
     * @param username имя пользователя (для проверки прав)
     * @return обновлённое объявление
     * @throws AccessDeniedException если у пользователя нет прав
     */
    public Ad updateAd(Integer id, CreateOrUpdateAd dto, String username) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));


        if (!isOwnerOrAdmin(entity, username)) {
            throw new AccessDeniedException("Нет прав");
        }

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        return adMapper.toDto(adRepository.save(entity));
    }

    /**
     * Удаляет объявление.
     *
     * @param id идентификатор объявления
     * @param username имя пользователя (для проверки прав)
     * @throws AccessDeniedException если у пользователя нет прав
     */
    public void deleteAd(Integer id, String username) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        if (!isOwnerOrAdmin(entity, username)) {
            throw new AccessDeniedException("Нет прав");
        }

        adRepository.delete(entity);
    }

    /**
     * Получает объявления текущего пользователя.
     *
     * @param username имя пользователя
     * @return список объявлений пользователя
     */
    public Ads getMyAds(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Ad> ads = adRepository.findAll()
                .stream()
                .filter(ad -> ad.getAuthor().equals(user))
                .map(adMapper::toDto)
                .collect(Collectors.toList());

        Ads result = new Ads();
        result.setResults(ads);
        result.setCount(ads.size());

        return result;
    }

    /**
     * Обновляет изображение объявления.
     *
     * @param id идентификатор объявления
     */
    public void updateImage(Integer id) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        adRepository.save(entity);
    }

    /**
     * Проверяет, является ли пользователь владельцем объявления
     * или имеет роль администратора.
     *
     * @param ad объявление
     * @param username имя пользователя
     * @return true, если есть доступ
     */
    private boolean isOwnerOrAdmin(AdEntity ad, String username) {
        boolean isOwner = ad.getAuthor().getUsername().equals(username);

        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return isOwner || isAdmin;
    }
}
