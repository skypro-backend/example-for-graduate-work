package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ImageService imageService;

    public AdServiceImpl(AdRepository adRepository,
                         UserRepository userRepository,
                         CommentRepository commentRepository,
                         ImageService imageService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.imageService = imageService;
    }

    //Метод для получения всех объявлений в виде DTO моделей
    @Override
    public AdsDTO getAllAds() {
        log.info("Fetching all ads");
        AdsDTO adsDTO = new AdsDTO();                                                      // Создание объекта AdsDTO, который будет содержать список AdDTO
        List<AdDTO> list = new ArrayList<>();                                             // Инициализация списка AdDTO
        adRepository.findAll().forEach(ad -> list.add(AdMapper.INSTANCE.adToAdDTO(ad))); // Получение всех объявлений из репозитория и преобразование их в AdDTO с использованием маппера
        adsDTO.setCount(list.size());                                                   // Установка общего количества объявлений в объект AdsDTO
        adsDTO.setResults(list);                                                        // Установка списка AdDTO в объект AdsDTO
        return adsDTO;
    }

    //Метод для добавления нового объявления
    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image) {
        log.info("Creating a new ad");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  // Получение информации о текущем пользователе из контекста безопасности
        User user = userRepository.findByEmail(authentication.getName());                        // Поиск пользователя в репозитории по его email (имени пользователя)
        Ad ad = AdMapper.INSTANCE.createOrUpdateAdDTOToAd(createOrUpdateAdDTO, user);             // Преобразование объекта CreateOrUpdateAdDTO в объект Ad с использованием маппера
        ad.setImage(imageService.addImage(image));                                               // Добавление изображения к объявлению с использованием сервиса изображений
        return AdMapper.INSTANCE.adToAdDTO(adRepository.save(ad));                               // Сохранение объявления в репозитории и преобразование его в AdDTO с использованием маппера
    }

    //Метод для получения информации об объявлении по id
    @Override
    public ExtendedAdDTO getAdInfo(Integer adId) {
        log.info("Fetching full details for ad with ID: {}", adId);
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);                           // Поиск объявления в репозитории по его ID
        User user = userRepository.findById(ad.getAuthor().getId()).orElseThrow(UserNotFoundException::new);  // Поиск автора объявления в репозитории по ID пользователя
        return AdMapper.INSTANCE.toExtendedAdDTO(ad, user);                                                    // Преобразование объявления и информации об авторе в ExtendedAdDTO с использованием маппера
    }

    //Метод для удаления объявления по id
    @Override
    public Void deleteAd(Integer adId) {
        log.info("Removing ad with ID: {}", adId);
        Integer imageId = adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getImage().getId(); // Получение идентификатора изображения, связанного с объявлением по его ID
        adRepository.deleteById(adId);                                                                           // Удаление объявления из репозитория по его ID
        imageService.deleteImage(imageId);                                                                       // Удаление изображения по его ID с использованием сервиса изображений
        commentRepository.deleteAllByAd_id(adId);                                                                 // Удаление всех комментариев, связанных с объявлением, из репозитория
        return null;                                                                                             // Возвращение null, поскольку метод возвращает Void
    }

    //Метод для изменения объявления
    @Override
    public AdDTO patchAd(Integer adId, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        log.info("Updating ad with ID: {}", adId);
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new); // Поиск объявления в репозитории по его ID
        // Обновление полей объявления данными из объекта createOrUpdateAdDTO
        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        return AdMapper.INSTANCE.adToAdDTO(adRepository.save(ad));               // Сохранение обновленного объявления в репозитории и преобразование его в AdDTO с использованием маппера
    }

    //Метод получения всех объявлений данного пользователя
    @Override
    public AdsDTO getAllAdsByAuthor() {
        log.info("Fetching ads for the authenticated user");
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());  // Получение информации о текущем пользователе из контекста безопасности
        List<AdDTO> list = new ArrayList<>();                                                                      // Инициализация списка AdDTO для хранения результатов
        adRepository.findAllByAuthor(user).forEach(u -> list.add(AdMapper.INSTANCE.adToAdDTO(u)));                 // Получение всех объявлений данного пользователя из репозитория и преобразование их в AdDTO с использованием маппера
        AdsDTO adsDTO = new AdsDTO();                                                                              // Создание объекта AdsDTO, который будет содержать список AdDTO
        adsDTO.setCount(list.size());                                                                              // Установка списка AdDTO в объект AdsDTO
        adsDTO.setResults(list);                                                                                   // Установка общего количества объявлений данного пользователя в объект AdsDTO
        return adsDTO;                                                                                             // Возвращение объекта AdsDTO, содержащего список объявлений данного пользователя и их общее количество
    }

    // Метод изменения фотографии у объявления
    @Override
    @Transactional
    public Void patchAdImage(Integer adId, MultipartFile image) {
        log.info("Updating image for ad with ID: {}", adId);
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);  // Поиск объявления в репозитории по его ID
        Integer imageId = ad.getImage().getId();                                    // Получение ID текущего изображения, связанного с объявлением
        ad.setImage(imageService.addImage(image));                                 // Замена изображения в объявлении новым изображением
        imageService.deleteImage(imageId);                                         // Удаление старого изображения по его ID с использованием сервиса изображений
        adRepository.save(ad);                                                     // Сохранение обновленного объявления в репозитории
        return null;                                                               // Возвращение null, поскольку метод возвращает Void
    }
}
