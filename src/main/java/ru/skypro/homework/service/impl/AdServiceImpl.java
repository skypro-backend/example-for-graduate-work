package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AccessDeniedException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Реализация сервиса объявлений.
 */
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    public AdServiceImpl(
            AdRepository adRepository,
            UserRepository userRepository,
            AdMapper adMapper
    ) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
    }

    /**
     * Возвращает все объявления.
     *
     * @return список объявлений
     */
    @Override
    @Transactional(readOnly = true)
    public Ads getAllAds() {
        List<Ad> results = adRepository
                .findAllByOrderByIdDesc()
                .stream()
                .map(adMapper::toDto)
                .toList();

        return new Ads(
                results.size(),
                results
        );
    }

    /**
     * Возвращает полную информацию об объявлении.
     *
     * @param id идентификатор
     * @return полное объявление
     */
    @Override
    @Transactional(readOnly = true)
    public ExtendedAd getAd(Long id) {
        return adMapper.toExtendedDto(
                findAd(id)
        );
    }

    /**
     * Создаёт объявление и сохраняет его изображение.
     *
     * @param username логин автора
     * @param properties данные объявления
     * @param imageBytes изображение
     * @param mediaType MIME-тип
     * @return созданное объявление
     */
    @Override
    @Transactional
    public Ad createAd(
            String username,
            CreateOrUpdateAd properties,
            byte[] imageBytes,
            String mediaType
    ) {
        UserEntity author = findUser(username);

        AdEntity ad = adMapper.toEntity(
                properties,
                author
        );

        ad.setImage(imageBytes);
        ad.setImageMediaType(mediaType);

        AdEntity savedAd =
                adRepository.save(ad);

        return adMapper.toDto(savedAd);
    }

    /**
     * Возвращает объявления текущего пользователя.
     *
     * @param username логин пользователя
     * @return список объявлений пользователя
     */
    @Override
    @Transactional(readOnly = true)
    public Ads getMyAds(String username) {
        UserEntity author = findUser(username);

        List<Ad> results = adRepository
                .findAllByAuthorOrderByIdDesc(author)
                .stream()
                .map(adMapper::toDto)
                .toList();

        return new Ads(
                results.size(),
                results
        );
    }

    /**
     * Обновляет объявление после проверки прав.
     *
     * @param id идентификатор объявления
     * @param username логин пользователя
     * @param role роль пользователя
     * @param properties новые данные
     * @return обновлённое объявление
     */
    @Override
    @Transactional
    public Ad updateAd(
            Long id,
            String username,
            String role,
            CreateOrUpdateAd properties
    ) {
        AdEntity ad = findAd(id);

        checkAccess(ad, username, role);

        adMapper.updateEntity(ad, properties);

        return adMapper.toDto(
                adRepository.save(ad)
        );
    }

    /**
     * Удаляет объявление после проверки прав.
     *
     * @param id идентификатор
     * @param username логин пользователя
     * @param role роль пользователя
     */
    @Override
    @Transactional
    public void deleteAd(
            Long id,
            String username,
            String role
    ) {
        AdEntity ad = findAd(id);

        checkAccess(ad, username, role);

        adRepository.delete(ad);
    }

    /**
     * Обновляет изображение объявления.
     *
     * @param id идентификатор
     * @param username логин пользователя
     * @param role роль пользователя
     * @param imageBytes изображение
     * @param mediaType MIME-тип
     */
    @Override
    @Transactional
    public void updateImage(
            Long id,
            String username,
            String role,
            byte[] imageBytes,
            String mediaType
    ) {
        AdEntity ad = findAd(id);

        checkAccess(ad, username, role);

        ad.setImage(imageBytes);
        ad.setImageMediaType(mediaType);

        adRepository.save(ad);
    }

    /**
     * Возвращает изображение объявления.
     *
     * @param id идентификатор
     * @return изображение
     */
    @Override
    @Transactional(readOnly = true)
    public byte[] getImage(Long id) {
        AdEntity ad = findAd(id);

        if (ad.getImage() == null) {
            throw new EntityNotFoundException(
                    "Изображение объявления с id "
                            + id
                            + " не найдено"
            );
        }

        return ad.getImage();
    }

    /**
     * Возвращает MIME-тип изображения.
     *
     * @param id идентификатор
     * @return MIME-тип
     */
    @Override
    @Transactional(readOnly = true)
    public String getImageMediaType(Long id) {
        AdEntity ad = findAd(id);

        if (ad.getImageMediaType() == null) {
            return "application/octet-stream";
        }

        return ad.getImageMediaType();
    }

    private void checkAccess(
            AdEntity ad,
            String username,
            String role
    ) {
        boolean owner = ad.getAuthor()
                .getUsername()
                .equalsIgnoreCase(username);

        boolean admin = "ADMIN".equals(role);

        if (!owner && !admin) {
            throw new AccessDeniedException(
                    "Недостаточно прав для изменения объявления"
            );
        }
    }

    private AdEntity findAd(Long id) {
        return adRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Объявление с id "
                                        + id
                                        + " не найдено"
                        )
                );
    }

    private UserEntity findUser(String username) {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Пользователь "
                                        + username
                                        + " не найден"
                        )
                );
    }
}
