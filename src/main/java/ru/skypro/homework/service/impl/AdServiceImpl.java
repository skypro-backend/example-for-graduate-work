package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.repo.AdRepository;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.service.AdMapper;
import ru.skypro.homework.service.AdService;
/**
 * Класс {@code AdServiceImpl} предоставляет реализацию интерфейса {@link AdService},
 * предоставляя функциональность для управления рекламными объявлениями в системе.
 *
 * <p>Инъекции зависимостей:
 * <ul>
 *     <li>{@code repository} - репозиторий для взаимодействия с данными рекламных объявлений.</li>
 *     <li>{@code mapper} - маппер для преобразования между сущностями и DTO рекламных объявлений.</li>
 * </ul>
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
 * <p>Этот класс является компонентом Spring с аннотацией {@link org.springframework.stereotype.Service},
 * предоставляющим бизнес-логику для управления рекламными объявлениями в системе.</p>
 *
 * @author Michail Z. (GH: HeimTN)
 */
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository repository;
    private final UserRepo userRepository;
    private final AdMapper mapper;

    public AdServiceImpl(AdRepository repository, AdMapper mapper, UserRepo userRepository){
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }


    @Override
    public Ads getAllAds() {
        return mapper.adToDtoList(repository.findAll());
    }

    @Override
    public Ad createAd(CreateOrUpdateAd ad, String image, Integer userId) {
        AdEntity result;
        result = mapper.dtoToAd(ad);
        result.setImage(image);
        result.setAuthor(userRepository.findById(userId).orElse(null));
        return mapper.adToDto(repository.save(result));
    }

    @Override
    public ExtendedAd getExtAd(Integer id) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        return mapper.adToExtDto(result);
    }

    @Override
    public Ad deleteAd(Integer id) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        repository.deleteById(id);
        return mapper.adToDto(result);
    }

    @Override
    public Ad pathAd(CreateOrUpdateAd ad, Integer id) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        result.setDescription(ad.getDescription());
        result.setTitle(ad.getTitle());
        result.setPrice(ad.getPrice());
        return mapper.adToDto(repository.save(result));
    }

    @Override
    public Ads getAllAdsForUser(Integer userId) {
        return mapper.adToDtoList(repository.findAdEntitiesByAuthor(userRepository.findById(userId).orElse(null)));
    }

    @Override
    public String pathImageAd(Integer id, String image) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        result.setImage(image);
        repository.save(result);
        return result.getImage();
    }
}
