package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exceptions.BlankFieldException;
import ru.skypro.homework.exceptions.MissingImageException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.service.AdsService;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {
    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);
    private final String imageDir;
    private AdEntityRepository adEntityRepository;
    private AdMapper adMapper;
//    private ImageEntity imageEntity;

    public AdsServiceImpl(AdEntityRepository adEntityRepository, AdMapper adMapper,
                          @Value("${path.to.images.folder}") String imageDir) {
        this.adEntityRepository = adEntityRepository;
        this.adMapper = adMapper;
        this.imageDir = imageDir;
//        this.imageEntity = imageEntity;
    }

    public Ads getAllAds() {

        List<AdEntity> adEntityList = adEntityRepository.findAll();
        if (adEntityList.isEmpty()) {
            return null;
        }

        List<Ad> adList = adMapper.adEntityListToAdList(adEntityList);

        return Ads.builder()
                .results(adList)
                .count(adList.size())
                .build();
    }

    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image) {

        if (properties.getTitle() == null || properties.getPrice() == null
                || properties.getDescription() == null || image.isEmpty()) {
            throw new BlankFieldException("Пустые поля при сохранении обьекта CreateOrUpdateAd " +
                    "или отсуствует фото сообщения!");
        }
        AdEntity adEntity = adMapper.AdToAdEntity(properties);

// туДу поменять строки внутри комментариев на метод по обработке фото
        Path filePath = Path.of(imageDir, adEntity.getTitle() + ".images");
        ImageEntity imageEntity = ImageEntity.builder()
                .filePath(filePath.toString())
                .mediaType(image.getContentType())
                .fileSize(image.getSize())
                .build();
        adEntity.setImageEntity(imageEntity);
//
        adEntityRepository.save(adEntity);
        return adMapper.AdEntityToAd(adEntity);
    }

    public ExtendedAd getAds(Integer adId) {

        ExtendedAd extendedAd = adMapper
                .AdEntityToExtendedAd(adEntityRepository.findById(adId).get());
        if (extendedAd.getPk() == null) {
            throw new EntityNotFoundException("Объявление в указанным id не найдено");
        }
        return extendedAd;
    }

    public void removeAd(Integer adId) {
        logger.info("Был вызван метод removeAd с данными id = " + adId);

        Optional<AdEntity> adEntity = adEntityRepository.findById(adId);

        if (adEntity.isEmpty()) {
            throw new EntityNotFoundException("Cущность с указанным id" +
                    "не найдена");
        }
        adEntityRepository.deleteById(adId);

        logger.info("Метод removeAd удалил " + "обьявление с id " + adId);
    }

    public CreateOrUpdateAd updateAd(Integer adId, CreateOrUpdateAd properties) {

        logger.info("Был вызван метод editFaculty с новыми данными обьявления " + properties);

        AdEntity adEntity = adEntityRepository.findById(adId)// откуда взять ай ди если в параметрах его нет
                .orElseThrow(() -> new IllegalArgumentException("Обьявление с указанным " + adId + " отсуствует!"));

        if (properties.getTitle() == null && properties.getPrice() == null
                && properties.getDescription() == null) {
            throw new BlankFieldException("Пустые поля при обновлении содержания обьявления");
        } else if (properties.getTitle() != null) {
            adEntity.setTitle(properties.getTitle());
        } else if (properties.getPrice() != null) {
            adEntity.setPrice(properties.getPrice());
        } else if (properties.getDescription() != null) {
            adEntity.setDescription(properties.getDescription());
        }
        adEntityRepository.save(adEntity);

        logger.info("Метод editFaculty обновил данные по  " + properties);
        return properties;
    }

    public Ads getAdsMe() {
        // туДу код который получает айДи авторизованного пользователя
        List<AdEntity> adEntityList = adEntityRepository
                .findByUserEntity_id(1);
        if (adEntityList.isEmpty()) {
            throw new EntityNotFoundException("У текущего пользвателя отсутствуют обьявления!");
        }
        List<Ad> adList = adMapper.adEntityListToAdList(adEntityList);

        return Ads.builder()
                .results(adList)
                .count(adList.size())
                .build();
    }

    public void updateImage(Integer adId, MultipartFile image) {

        logger.info("Был вызван метод updateImage для обьявления с adId" + adId);
        AdEntity adEntity = adEntityRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Обьявление с указанным " + adId + " отсуствует!"));

        if (!image.getContentType().equals("image/jpeg") || !image.getContentType().equals("image/png")) {
            throw new MissingImageException("Некорректный формат изображения обьявления!");
        }

        // туДу поменять строки внутри комментариев на метод по обработке фото
        Path filePath = Path.of(imageDir, adEntity.getTitle() + ".images");
        ImageEntity imageEntity = ImageEntity.builder()
                .filePath(filePath.toString())
                .mediaType(image.getContentType())
                .fileSize(image.getSize())
                .build();
        adEntity.setImageEntity(imageEntity);
        //
        logger.info("Метод updateImage вернул адрес обновленного фото: " + imageEntity.getFilePath());

    }


}
