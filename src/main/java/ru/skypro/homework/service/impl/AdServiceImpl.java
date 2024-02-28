package ru.skypro.homework.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.repositories.AdRepository;
import ru.skypro.homework.repositories.PhotoRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
//@AllArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final PhotoRepository photoRepository;
    private final AdMapper adMapper;
    private final ImageServiceImpl imageService;
    private final UserServiceImpl userService;
    @Value("${path.to.photos.folder}")
    private String photoDir;

    public AdServiceImpl(AdRepository adRepository,
                         PhotoRepository photoRepository,
                         AdMapper adMapper,
                         ImageServiceImpl imageService,
                         UserServiceImpl userService) {
        this.adRepository = adRepository;
        this.photoRepository = photoRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
        this.userService = userService;
    }


    @Override
    public Ads getAllAds() {
        List<Ad> dtos = adRepository.findAll().stream()
                .map(entity -> adMapper.mapToAdDto(entity))
                .collect(Collectors.toList());
        return new Ads(dtos.size(), dtos);
    }


    @Override
    public Ad addAd(CreateOrUpdateAd properties,
                    MultipartFile image,
                    Authentication authentication) throws IOException {
        //создаем сущность
        AdEntity adEntity = new AdEntity();

        //заполняем поля title, price и description, которые берутся из properties
        adEntity.setTitle(properties.getTitle());
        adEntity.setPrice(properties.getPrice());
        adEntity.setDescription(properties.getDescription());

        //заполняем поле author
        adEntity.setAuthor(userService.getUser(authentication.getName()));
        //заполняем поля
        adEntity = (AdEntity) imageService.updateEntitiesPhoto(image, adEntity);

        //сохранение сущности adEntity в БД
        adRepository.save(adEntity);

        //возврат ДТО Ad из метода
        return adMapper.mapToAdDto(adEntity);
    }


    @Override
    public ExtendedAd getAds(Integer id) {
        AdEntity entity = adRepository.findById(id).get();
        return adMapper.mapToExtendedAdDto(entity);
    }


    @Transactional
    @Override
    public boolean removeAd(Integer id) throws IOException {
        AdEntity ad = adRepository.findById(id).get();
        if (ad != null) {
            //удаление объявления из БД
            adRepository.delete(ad);
            //удаление фото из БД
            photoRepository.delete(ad.getPhoto());
            //получение пути из сущности объявления
            String filePath = ad.getFilePath();
            //создание пути к файлу
            Path path = Path.of(filePath);
            //удаление файла с ПК
            Files.delete(path);
            return true;
        } else {
            return false;
        }
    }


    @Transactional
    @Override
    public Ad updateAds(Integer id, CreateOrUpdateAd dto) {
        AdEntity entity = adRepository.findById(id).get();

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        adRepository.save(entity);
        return adMapper.mapToAdDto(entity);
    }


    @Override
    @Transactional
    public Ads getAdsMe(String username) {
        UserEntity author = userService.getUser(username);
        List<Ad> ads = null;
        ads = adRepository.findByAuthor(author).stream()
                .map(ad -> adMapper.mapToAdDto(ad))
                .collect(Collectors.toList());
        Ads adsDto = new Ads(ads.size(), ads);
        return adsDto;
    }

    @Transactional
    @Override
    public void updateImage(Integer id, MultipartFile image) throws IOException {
        //достаем объявление из БД
        AdEntity adEntity = adRepository.findById(id).orElseThrow(RuntimeException::new);
        //заполняю поля и получаю сущность в переменную
        adEntity = (AdEntity) imageService.updateEntitiesPhoto(image, adEntity);
        //сохранение сущности user в БД
        adRepository.save(adEntity);
    }

    public boolean isAuthorAd(String username, Integer adId) {

        AdEntity adEntity = adRepository.findById(adId).orElseThrow(RuntimeException::new);
        return adEntity.getAuthor().getUserName().equals(username);
    }

}
