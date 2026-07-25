package com.skypro.service.impl;

import com.skypro.dto.Ad;
import com.skypro.dto.Ads;
import com.skypro.dto.CreateOrUpdateAd;
import com.skypro.dto.ExtendedAd;
import com.skypro.entity.AdEntity;
import com.skypro.entity.UserEntity;
import com.skypro.exception.AdNotFoundException;
import com.skypro.exception.UserNotFoundException;
import com.skypro.mapper.AdMapper;
import com.skypro.repository.AdRepository;
import com.skypro.repository.UserRepository;
import com.skypro.service.AdService;
import com.skypro.service.ImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса {@link AdService} для управления объявлениями.
 * <p>
 * Содержит бизнес-логику:
 * <ul>
 *   <li>Получение всех объявлений и объявлений текущего пользователя</li>
 *   <li>Создание, обновление и удаление объявлений</li>
 *   <li>Проверка прав доступа через {@link PreAuthorize}</li>
 *   <li>Работа с изображениями через {@link ImageService}</li>
 * </ul>
 * </p>
 */
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;

    public AdServiceImpl(AdRepository adRepository,
                         UserRepository userRepository,
                         AdMapper adMapper,
                         ImageService imageService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
    }

    /**
     * Возвращает список всех объявлений.
     *
     * @return объект {@link Ads}, содержащий количество и список всех объявлений
     */
    @Override
    public Ads getAllAds() {
        List<AdEntity> adEntities = adRepository.findAll();
        List<Ad> ads = adEntities.stream()
                .map(adMapper::toAd)
                .collect(Collectors.toList());
        return new Ads(ads.size(), ads);
    }

    /**
     * Создаёт новое объявление для указанного пользователя.
     *
     * @param createOrUpdateAd данные объявления (заголовок, цена, описание)
     * @param image            файл изображения для объявления
     * @param username         имя пользователя (автора) из аутентификации
     * @return созданное объявление в виде {@link Ad}
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        AdEntity adEntity = adMapper.toEntity(createOrUpdateAd);
        adEntity.setAuthor(user);
        adEntity.setImage(imageService.saveAdImage(image));
        adEntity.setCreatedAt(System.currentTimeMillis());
        AdEntity saved = adRepository.save(adEntity);
        return adMapper.toAd(saved);
    }

    /**
     * Возвращает детальную информацию об объявлении по его идентификатору.
     *
     * @param id идентификатор объявления
     * @return DTO {@link ExtendedAd} с полной информацией об объявлении и авторе
     * @throws AdNotFoundException если объявление не найдено
     */
    @Override
    public ExtendedAd getAd(Integer id) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException(id));
        return adMapper.toExtendedAd(adEntity);
    }

    /**
     * Обновляет данные существующего объявления.
     * <p>
     * Доступно только владельцу объявления или администратору.
     * </p>
     *
     * @param id               идентификатор объявления
     * @param createOrUpdateAd новые данные (заголовок, цена, описание)
     * @return обновлённое объявление в виде {@link Ad}
     * @throws AdNotFoundException если объявление не найдено
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является владельцем и не ADMIN
     */
    @Override
    @PreAuthorize("@securityService.isAdOwner(#id, authentication.name) || hasRole('ADMIN')")
    public Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException(id));
        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setPrice(createOrUpdateAd.getPrice());
        adEntity.setDescription(createOrUpdateAd.getDescription());
        AdEntity saved = adRepository.save(adEntity);
        return adMapper.toAd(saved);
    }

    /**
     * Удаляет объявление по идентификатору.
     * <p>
     * Доступно только владельцу объявления или администратору.
     * </p>
     *
     * @param id идентификатор объявления
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является владельцем и не ADMIN
     */
    @Override
    @PreAuthorize("@securityService.isAdOwner(#id, authentication.name) || hasRole('ADMIN')")
    public void removeAd(Integer id) {
        adRepository.deleteById(id);
    }

    /**
     * Возвращает список объявлений, принадлежащих текущему пользователю.
     *
     * @param username имя пользователя из аутентификации
     * @return объект {@link Ads} с объявлениями пользователя
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public Ads getAdsMe(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        List<AdEntity> adEntities = adRepository.findAllByAuthorId(user.getId());
        List<Ad> ads = adEntities.stream()
                .map(adMapper::toAd)
                .collect(Collectors.toList());
        return new Ads(ads.size(), ads);
    }

    /**
     * Обновляет изображение объявления.
     * <p>
     * Доступно только владельцу объявления или администратору.
     * </p>
     *
     * @param id    идентификатор объявления
     * @param image новый файл изображения
     * @throws AdNotFoundException если объявление не найдено
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является владельцем и не ADMIN
     */
    @Override
    @PreAuthorize("@securityService.isAdOwner(#id, authentication.name) || hasRole('ADMIN')")
    public void updateImage(Integer id, MultipartFile image) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException(id));
        adEntity.setImage(imageService.saveAdImage(image));
        adRepository.save(adEntity);
    }
}
