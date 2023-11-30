package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.mapper.AdsMapper;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Класс-сервис, реализующий интерфейс {@link AdsService}
 */
@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final ImageRepository imageRepository;
    private final AdsMapper adsMapper;
    private final AuthServiceImpl authService;
    private final ImageService imageService;
    Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);

    /**
     * Метод возврщает список и количество всех объявлений
     * @return {@link AllAdsDTO}
     */
    @Override
    @Transactional
    public AllAdsDTO getAllAds() {
        List<AdsDTO> adsDTOList = adsRepository.findAll().stream()
                .map(adsMapper ::adsToAdsDto)
                .collect(Collectors.toList());

        AllAdsDTO allAdsDTO = new AllAdsDTO();
        allAdsDTO.setResults(adsDTOList);
        allAdsDTO.setCount(adsDTOList.size());

        logger.info("Получен список всех объявлений");
        return allAdsDTO;
    }

    /**
     * Метод для добавления объявления
     * @param image
     * @param properties
     * @return {@link AdsDTO}
     */
    @Override
    @Transactional
    public AdsDTO addAds(MultipartFile image, CreateAdsDTO properties) {
        UserInfo user = authService.getCurrentUser();

        Image uploadImage = imageService.uploadImage(image);
        imageRepository.save(uploadImage);
        logger.info("Изображение объявления сохранено в базу данных", uploadImage);
        Ads ads = adsMapper.createAdsDtoToModel(properties);
        ads.setImage(uploadImage);
        ads.setAuthor(user);
        adsRepository.save(ads);

        logger.info("Объявление сохранено в базу данных", ads);
        return adsMapper.adsToAdsDto(ads);

    }

    /**
     * Метод возвращает информацию об оъбявлении по id
     * @param id
     * @return {@link AdsInfoDTO}
     */
    @Override
    @Transactional
    public AdsInfoDTO getAdsById(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);

        return adsMapper.adsToAdsInfoDto(ads);
    }

    /**
     * Метод удаляет объявление по id
     * @param id
     */
    @Override
    @Transactional
    public void deleteAds(Integer id) {
        UserInfo user = authService.getCurrentUser();
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        if (user.getId().equals(ads.getAuthor().getId()) || user.getRole().equals(Role.ADMIN)) {
            adsRepository.deleteById(id);
        }
        logger.info("Обявление удалено из базы данных", ads);
    }

    /**
     * Метод обновляет информацию об обяъвлении
     * @param id
     * @param createAdsDTO
     * @return
     */
    @Override
    @Transactional
    public AdsDTO updateAds(Integer id, CreateAdsDTO createAdsDTO) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        UserInfo user = authService.getCurrentUser();
        if (user.getId().equals(ads.getAuthor().getId()) || user.getRole().equals(Role.ADMIN)) {
            ads.setDescription(createAdsDTO.getDescription());
            ads.setPrice(createAdsDTO.getPrice());
            ads.setTitle(createAdsDTO.getTitle());
        }

        return adsMapper.adsToAdsDto(ads);
    }

    /**
     * Метод возвращает список и количество всех объявлений пользователя
     * @return {@link AllAdsDTO}
     */
    @Override
    @Transactional
    public AllAdsDTO getUserAds() {
        UserInfo user = authService.getCurrentUser();
        List<AdsDTO> adsDTOList = user.getAds().stream()
                .map(adsMapper::adsToAdsDto)
                .collect(Collectors.toList());

        AllAdsDTO allAdsDTO = new AllAdsDTO();
        allAdsDTO.setResults(adsDTOList);
        allAdsDTO.setCount(adsDTOList.size());
        logger.info("Получен список всех объявлений пользователя");
        return allAdsDTO;
    }

    /**
     * Метод обновляет изображение объявления
     * @param id
     * @param image
     */
    @Override
    @Transactional
    public void updateAdsImage(Integer id, MultipartFile image) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        UserInfo user = authService.getCurrentUser();
        if (user.getId().equals(ads.getAuthor().getId()) || user.getRole().equals(Role.ADMIN)) {
            Image uploadImage = imageService.uploadImage(image);
            ads.setImage(uploadImage);
            adsRepository.save(ads);
        }
    }

    /**
     * Метод возвращает массив байтов изображения по id
     * @param id
     * @return byte[]
     */
    @Override
    @Transactional
    public byte[] getImage(String id) {
        return imageService.getImage(id);
    }
}
