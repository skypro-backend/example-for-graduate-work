package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
/**
 * Интерфейс {@code AdService} определяет методы, предоставляющие функциональность
 * для управления рекламными объявлениями в системе.
 *
 * <p>Методы сервиса:</p>
 * <ul>
 *     <li>{@code getAllAds} - возвращает все рекламные объявления в виде объекта {@link Ads}.</li>
 *     <li>{@code createAd} - создает новое рекламное объявление на основе данных из объекта
 *     {@link CreateOrUpdateAd}, изображения и идентификатора пользователя. Возвращает созданное объявление.</li>
 *     <li>{@code getExtAd} - возвращает расширенную информацию о рекламном объявлении по его идентификатору.</li>
 *     <li>{@code deleteAd} - удаляет рекламное объявление по его идентификатору. Возвращает удаленное объявление.</li>
 *     <li>{@code pathAd} - обновляет рекламное объявление с использованием данных из объекта {@link CreateOrUpdateAd}
 *     по его идентификатору. Возвращает обновленное объявление.</li>
 *     <li>{@code getAllAdsForUser} - возвращает все рекламные объявления для указанного пользователя
 *     в виде объекта {@link Ads}.</li>
 *     <li>{@code pathImageAd} - обновляет изображение рекламного объявления по его идентификатору.
 *     Возвращает путь к обновленному изображению.</li>
 * </ul>
 *
 * <p>Этот интерфейс служит в качестве контракта для уровня сервиса и определяет методы,
 * необходимые для работы с рекламными объявлениями в системе.</p>
 *
 * @author Michail Z. (GH: HeimTN)
 */
public interface AdService {
    Ads getAllAds();
    Ad createAd(CreateOrUpdateAd ad, MultipartFile image);
    ExtendedAd getExtAd(Integer id);
    Ad deleteAd(Integer id);
    Ad pathAd(CreateOrUpdateAd ad, Integer id);
    Ads getAllAdsForUser();
    MultipartFile pathImageAd(Integer id, MultipartFile image);
    byte[] getImageAd(Integer id);
}
