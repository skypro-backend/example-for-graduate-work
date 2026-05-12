package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

import java.io.IOException;

/**
 * Сервис для управления объявлениями.
 * <p>
 * Определяет контракт бизнес-логики, связанной с объявлениями:
 * <ul>
 *   <li>Получение списка и детальной информации</li>
 *   <li>Создание, редактирование и удаление</li>
 *   <li>Управление изображениями</li>
 *   <li>Фильтрация по автору</li>
 * </ul>
 * Все операции требуют аутентификации, за исключением получения общего списка и просмотра отдельного объявления.
 * Проверка прав доступа (автор или администратор) выполняется внутри реализации.
 *
 * @see AdServiceImpl — основная реализация интерфейса
 * @see AdController — использует этот сервис для обработки HTTP-запросов
 * @see ImageService — используется при обновлении изображения объявления
 */
public interface AdService {

    /**
     * Получает все объявления в системе.
     * <p>
     * Доступно без авторизации. Возвращает краткую информацию обо всех объявлениях.
     *
     * @return объект {@link Ads}, содержащий общее количество и список объявлений
     */
    Ads getAllAds();

    /**
     * Получает расширенную информацию об объявлении по его идентификатору.
     * <p>
     * Включает описание, контактные данные автора, цену, заголовок и ссылки на изображения.
     *
     * @param id идентификатор объявления
     * @return объект {@link ExtendedAd}
     * @throws EntityNotFoundException если объявление с таким ID не найдено
     */
    ExtendedAd getExtendedAd(Integer id);

    /**
     * Создаёт новое объявление.
     * <p>
     * Устанавливает автором пользователя, определённого по email. Фиксирует текущее время как дату создания.
     *
     * @param adEntity сущность объявления с заполненными полями (без ID и автора)
     * @param email    email текущего пользователя (автора)
     * @return созданный объект {@link Ad} (DTO)
     * @throws EntityNotFoundException если пользователь не найден
     */
    Ad createAd(AdEntity adEntity, String email);

    /**
     * Удаляет объявление по его идентификатору.
     * <p>
     * Доступно только автору объявления или администратору.
     *
     * @param id    идентификатор объявления
     * @param email email текущего пользователя
     * @throws EntityNotFoundException если объявление или пользователь не найдены
     * @throws SecurityException       если нет прав на удаление
     */
    void deleteAd(Integer id, String email);

    /**
     * Частично обновляет данные объявления.
     * <p>
     * Доступно только автору или администратору. Обновляет: заголовок, цену, описание.
     *
     * @param id        идентификатор объявления
     * @param updatedAd сущность с новыми данными
     * @param email     email текущего пользователя
     * @return обновлённый объект {@link Ad}
     * @throws EntityNotFoundException если объявление или пользователь не найдены
     * @throws SecurityException       если нет прав на редактирование
     */
    Ad updateAd(Integer id, AdEntity updatedAd, String email);

    /**
     * Получает все объявления текущего пользователя.
     * <p>
     * Используется при открытии страницы "Мои объявления".
     *
     * @param email email пользователя
     * @return объект {@link Ads}, содержащий список его объявлений
     * @throws EntityNotFoundException если пользователь не найден
     */
    Ads getAdsByUser(String email);

    /**
     * Обновляет изображение объявления.
     * <p>
     * Доступно только автору или администратору. Сохраняет изображение через {@link ImageService}.
     *
     * @param id    идентификатор объявления
     * @param image байты нового изображения
     * @param email email текущего пользователя
     * @throws IOException             при ошибке записи файла
     * @throws EntityNotFoundException если объявление или пользователь не найдены
     * @throws SecurityException       если нет прав на обновление
     */
    void updateImage(Integer id, byte[] image, String email) throws IOException;
}