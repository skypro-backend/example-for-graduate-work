package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.controller.AdController;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для управления объявлениями.
 * <p>
 * Обеспечивает бизнес-логику:
 * <ul>
 *   <li>Получение списка и деталей объявлений</li>
 *   <li>Создание, редактирование и удаление</li>
 *   <li>Управление изображениями</li>
 *   <li>Проверка прав доступа (автор или администратор)</li>
 * </ul>
 * Все операции выполняются через репозитории {@link AdRepository} и {@link UserRepository},
 * преобразование данных — с помощью {@link AdMapper}.
 *
 * @see AdService — основной интерфейс, используемый контроллерами
 * @see AdController — использует этот сервис для обработки HTTP-запросов
 * @see ImageService — для сохранения изображений
 */
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    /**
     * Логгер для отладки и мониторинга операций сервиса.
     */
    private static final Logger log = LoggerFactory.getLogger(AdServiceImpl.class);

    /**
     * Репозиторий для доступа к данным объявлений.
     */
    private final AdRepository adRepository;

    /**
     * Репозиторий для доступа к данным пользователей (проверка авторства и прав).
     */
    private final UserRepository userRepository;

    /**
     * Маппер для преобразования сущности {@link AdEntity} в DTO ({@link Ad}, {@link ExtendedAd}).
     */
    private final AdMapper adMapper;

    /**
     * Сервис для работы с изображениями объявлений.
     */
    private final ImageService imageService;

    /**
     * Получение всех объявлений в системе.
     * <p>
     * Возвращает список краткой информации обо всех объявлениях.
     * Используется при открытии главной страницы.
     *
     * @return объект {@link Ads}, содержащий общее количество и список объявлений
     */
    @Override
    public Ads getAllAds() {
        List<Ad> ads = adRepository.findAll().stream().map(adMapper::toDto).collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);
        return result;
    }

    /**
     * Получение расширенной информации об объявлении по ID.
     * <p>
     * Возвращает полные данные: описание, контактные данные автора, цена, заголовок, ссылки на изображения.
     *
     * @param id идентификатор объявления
     * @return объект {@link ExtendedAd}
     * @throws EntityNotFoundException если объявление не найдено
     */
    @Override
    public ExtendedAd getExtendedAd(Integer id) {
        AdEntity ad = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));
        return adMapper.toExtendedDto(ad);
    }

    /**
     * Создание нового объявления.
     * <p>
     * Устанавливает автором пользователя, определённого по email.
     * Фиксирует текущее время как дату создания.
     *
     * @param adEntity сущность объявления с заполненными полями (без ID и автора)
     * @param email    email текущего пользователя (автора)
     * @return созданный объект {@link Ad} (DTO)
     * @throws EntityNotFoundException если пользователь не найден
     */
    @Override
    public Ad createAd(AdEntity adEntity, String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        adEntity.setAuthor(user);
        adEntity.setCreatedAt(java.time.LocalDateTime.now());

        log.debug("Before save: adEntity.pk = {}", adEntity.getPk());
        AdEntity saved = adRepository.save(adEntity);
        log.debug("After save: saved.pk = {}", saved.getPk());

        return adMapper.toDto(saved);
    }

    /**
     * Удаление объявления по ID.
     * <p>
     * Доступно только автору объявления или администратору.
     *
     * @param id    идентификатор объявления
     * @param email email текущего пользователя
     * @throws EntityNotFoundException если объявление или пользователь не найдены
     * @throws ResponseStatusException с кодом 403, если нет прав на удаление
     */
    @Override
    public void deleteAd(Integer id, String email) {
        AdEntity ad = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!ad.getAuthor().getId().equals(user.getId()) && !user.getRole().name().equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "У вас нет прав на удаление этого объявления");
        }

        adRepository.deleteById(id);
    }

    /**
     * Частичное обновление объявления.
     * <p>
     * Доступно только автору или администратору.
     * Обновляет только указанные поля: заголовок, цена, описание.
     *
     * @param id        идентификатор объявления
     * @param updatedAd сущность с новыми данными
     * @param email     email текущего пользователя
     * @return обновлённый объект {@link Ad}
     * @throws EntityNotFoundException если объявление или пользователь не найдены
     * @throws SecurityException       если нет прав на редактирование
     */
    @Override
    public Ad updateAd(Integer id, AdEntity updatedAd, String email) {
        AdEntity existing = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!existing.getAuthor().getId().equals(user.getId()) && !user.getRole().name().equals("ADMIN")) {
            throw new SecurityException("У вас нет прав на редактирование этого объявления");
        }

        existing.setTitle(updatedAd.getTitle());
        existing.setPrice(updatedAd.getPrice());
        existing.setDescription(updatedAd.getDescription());
        AdEntity saved = adRepository.save(existing);

        return adMapper.toDto(saved);
    }

    /**
     * Получение всех объявлений текущего пользователя.
     * <p>
     * Используется при открытии страницы "Мои объявления".
     *
     * @param email email пользователя
     * @return объект {@link Ads}, содержащий список его объявлений
     * @throws EntityNotFoundException если пользователь не найден
     */
    @Override
    public Ads getAdsByUser(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        List<Ad> ads = adRepository.findByAuthorId(user.getId()).stream().map(adMapper::toDto).collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);
        return result;
    }

    /**
     * Обновление изображения объявления.
     * <p>
     * Доступно только автору или администратору.
     * Сохраняет изображение через {@link ImageService}.
     *
     * @param id    идентификатор объявления
     * @param image байты нового изображения
     * @param email email текущего пользователя
     * @throws IOException             при ошибке записи файла
     * @throws EntityNotFoundException если объявление или пользователь не найдены
     * @throws SecurityException       если нет прав на обновление
     */
    @Override
    public void updateImage(Integer id, byte[] image, String email) throws IOException {
        AdEntity ad = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!ad.getAuthor().getId().equals(user.getId()) && !user.getRole().name().equals("ADMIN")) {
            throw new SecurityException("Нет прав на обновление изображения");
        }

        imageService.saveAdImage(ad.getPk(), image);
    }
}