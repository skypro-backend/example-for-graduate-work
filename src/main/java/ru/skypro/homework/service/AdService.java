package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с объявлениями.
 * Содержит бизнес-логику создания, получения, обновления и удаления объявлений.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;

    /**
     * Получить все объявления, отсортированные по дате создания (сначала новые).
     *
     * @return список всех объявлений в виде {@link AdDto}
     */
    @Transactional(readOnly = true)
    public List<AdDto> getAllAds() {
        List<AdEntity> ads = adRepository.findAllByOrderByCreatedAtDesc();
        return adMapper.toAdDtoList(ads);
    }

    /**
     * Получить объявление по его ID.
     *
     * @param id идентификатор объявления
     * @return расширенное DTO объявления {@link ExtendedAdDto}
     * @throws EntityNotFoundException если объявление не найдено
     */
    @Transactional(readOnly = true)
    public ExtendedAdDto getAdById(Integer id) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с id " + id + " не найдено"));
        return adMapper.toExtendedAdDto(entity);
    }

    /**
     * Получить все объявления текущего авторизованного пользователя.
     *
     * @param email email пользователя
     * @return список объявлений пользователя
     */
    @Transactional(readOnly = true)
    public List<AdDto> getMyAds(String email) {
        List<AdEntity> ads = adRepository.findByAuthorEmailIgnoreCase(email);
        return adMapper.toAdDtoList(ads);
    }

    /**
     * Создать новое объявление.
     *
     * @param dto    данные для создания объявления
     * @param author автор объявления (текущий пользователь)
     * @return созданное объявление в виде {@link AdDto}
     */
    public AdDto createAd(CreateOrUpdateAdDto dto, UserEntity author) {
        AdEntity entity = adMapper.toEntity(dto);
        entity.setAuthor(author);
        entity.setCreatedAt(LocalDateTime.now());
        entity = adRepository.save(entity);
        log.info("Создано объявление с id {} пользователем {}", entity.getId(), author.getEmail());
        return adMapper.toAdDto(entity);
    }

    /**
     * Обновить существующее объявление.
     * Пользователь может обновить только своё объявление, администратор — любое.
     *
     * @param id               идентификатор объявления
     * @param dto              новые данные
     * @param currentUserEmail email текущего пользователя
     * @param isAdmin          флаг, является ли пользователь администратором
     * @return обновлённое объявление в виде {@link AdDto}
     * @throws EntityNotFoundException если объявление не найдено
     * @throws SecurityException       если у пользователя нет прав на редактирование
     */
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto dto, String currentUserEmail, boolean isAdmin) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с id " + id + " не найдено"));

        if (!isAdmin && !entity.getAuthor().getEmail().equalsIgnoreCase(currentUserEmail)) {
            throw new SecurityException("Нет прав на редактирование чужого объявления");
        }

        adMapper.updateEntity(entity, dto);
        entity = adRepository.save(entity);
        log.info("Обновлено объявление с id {}", id);
        return adMapper.toAdDto(entity);
    }

    /**
     * Удалить объявление.
     * Пользователь может удалить только своё объявление, администратор — любое.
     *
     * @param id               идентификатор объявления
     * @param currentUserEmail email текущего пользователя
     * @param isAdmin          флаг, является ли пользователь администратором
     * @throws EntityNotFoundException если объявление не найдено
     * @throws SecurityException       если у пользователя нет прав на удаление
     */
    public void deleteAd(Integer id, String currentUserEmail, boolean isAdmin) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с id " + id + " не найдено"));

        if (!isAdmin && !entity.getAuthor().getEmail().equalsIgnoreCase(currentUserEmail)) {
            throw new SecurityException("Нет прав на удаление чужого объявления");
        }

        adRepository.delete(entity);
        log.info("Удалено объявление с id {} пользователем {}", id, currentUserEmail);
    }
}