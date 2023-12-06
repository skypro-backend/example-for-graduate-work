package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.PhotoEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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

//    @Override
    public Ad addAd(CreateOrUpdateAd properties,
                    MultipartFile image,
                    Authentication authentication) throws IOException {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        //создаем сущность
        AdEntity adEntity = new AdEntity();

        //заполняем поля title, price и description
        adEntity.setTitle(properties.getTitle());
        adEntity.setPrice(properties.getPrice());
        adEntity.setDescription(properties.getDescription());


        //заполняем поля photo и author
        adEntity.setPhoto(adMapper.mapMultipartFileToPhoto(image));
        adEntity.setAuthor(userService.getUser(authentication));
        //записываем URL для перехода фронта к методу возврата photo
        String urlToPhoto = "/photo/image/" + adEntity.getPhoto().getId();
        adEntity.setImage(urlToPhoto);
        log.info("URL для перехода фронта к методу возврата photo: {}", urlToPhoto);

        //адрес до директории хранения фото на ПК
        Path filePath = Path.of(photoDir, adEntity.getPhoto().getId() +/* "-" + properties.getTitle() + */"."
                + imageService.getExtension(image.getOriginalFilename()));
        log.info("путь к файлу: {}", filePath);

        //сохранение на ПК
        imageService.saveFileOnDisk(adEntity.getPhoto(), filePath);

        //сохранение сущности adEntity в БД
        adRepository.save(adEntity);

        //возврат ДТО Ad из метода
        return adMapper.mapToAdDto(adEntity);
    }

//    @Override
//    public Ad addAd1(CreateOrUpdateAd properties,
//                    MultipartFile image,
//                    Authentication authentication) throws IOException {
//        log.info("Запущен метод сервиса: addAd");
//        //Создаем сущность adEntity и мапим в нее полученную с фронта ДТО.
//        //Заполняются 3/7 полей: title, price, description.
//        AdEntity adEntity = adMapper.mapToAdEntity(properties, userService.getUser(authentication).getUserName());
//        //формируем путь к картинке в папке проекта
//        Path filePath = Path.of(photoDir, adEntity.getId() + "-" + properties.getTitle() + "."
//                + imageService.getExtension(image.getOriginalFilename()));
//        log.info("путь файла в папке проекта: {}", filePath);
//
//        //мапим MultypartFile в PhotoEntity
//        PhotoEntity photo = new PhotoEntity();
//        photo.setData(image.getBytes());
//        photo.setMediaType(image.getContentType());
//        photo.setFileSize(image.getSize());
//        log.info("сущность photo = {}", photo);
//        //сохраняем картинку в папку проекта
//        log.info("MultipartFile сохранен в папку проекта - {}", imageService.saveFileOnDisk(photo, filePath));
//
//        //Заполняем поле photo в сущности adEntity (заполнено 4/7 полей)
//        adEntity.setPhoto(imageService.updateAdImage(adEntity, image, filePath));
//        //Заполняем поле image (заполнено 5/7 полей) URL куда будет обращаться фронт с добавлением имени картинки
//        adEntity.setImage("/" + photoDir + "/" + adEntity.getId());
//        //заполняем поле author (заполнено 6/7 полей) текущим авторизованным пользователем
//        //незаполненным осталось поле comments - коллекция комментариев
//        adEntity.setAuthor(userService.getUser(authentication));
//        //сохраняем сущность в репозиторий
//        log.info("Сущность adEntity - {}", adRepository.save(adEntity));
//        //мапим сущность adEntity в ДТО Ad и возвращаем его из метода
//        Ad adDTO = adMapper.mapToAdDto(adEntity);
//        log.info("ДТО Ad - {}", adDTO);
//        return adDTO;
//    }


//    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image) {
//        log.info("Использован метод сервиса addAd - ВАРИАНТ2");
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserEntity user = userService.getUser(auth);
//        log.info("Сущность UserEntity создана");
//        AdEntity adEntity = adMapper.mapToAdEntity(properties, user.getUserName());
//        log.info("Сущность adEntity создана");
//        adEntity.setPhoto(imageService.addPhoto(image));
//        log.info("Добавлено фото в adEntity");
//        Ad adDto = adMapper.mapToAdDto(adRepository.save(adEntity));
//        log.info("ДТО ad создано");
//        return adDto;
//    }

    @Override
    public ExtendedAd getAds(Integer id) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        AdEntity entity = adRepository.findById(id).get();
        return adMapper.mapToExtendedAdDto(entity);
    }

    @Override
    public boolean removeAd(Integer id) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        AdEntity ad = adRepository.findById(id).get();
        if (ad != null) {
            adRepository.delete(ad);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Ad updateAds(Integer id, CreateOrUpdateAd dto) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
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
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        UserEntity author = userService.checkUserByUsername(username);
        log.info("объект UserEntity получен из БД");
        List<Ad> ads = null;
            ads = adRepository.findByAuthor(author).stream()
                    .map(ad -> adMapper.mapToAdDto(ad))
                    .collect(Collectors.toList());
        log.info("Получен список объявлений пользователя ads");
        Ads adsDto = new Ads(ads.size(), ads);
        log.info("Сформирован возвращаемый объект adsDto");
        return adsDto;
    }

    @Override
    public void updateImage(Integer id, MultipartFile image) throws IOException {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        /*Optional<AdEntity> entity = adRepository.findById(id);
        if (entity.isPresent()) {
            PhotoEntity photo = new PhotoEntity();
            Path path = Path.of(photoDir);
            if (!path.toFile().exists()) {
                Files.createDirectories(path);
            }
            var dotIndex = Objects.requireNonNull(image.getOriginalFilename()).lastIndexOf('.');
            var ext = image.getOriginalFilename().substring(dotIndex + 1);
            var photoPath = photoDir + "/" + entity.get().getTitle() + "." + ext;
            try (var in = image.getInputStream();
                 var out = new FileOutputStream(photoPath)) {
                in.transferTo(out);
            }
            photo.setFilePath(photoPath);
            photo.setData(image.getBytes());
            photo.setFileSize(image.getSize());
            photo.setMediaType(image.getContentType());
            photo.setAd(entity.get());
            return photoRepository.save(photo);
        } else {
            return null;
        }*/

//        AdEntity adEntity = adRepository.findById(id).get();

        // todo продумать путь до файла
        Path filePath = Path.of(photoDir, id + "."
                + imageService.getExtension(image.getOriginalFilename()));

        imageService.saveFileOnDisk(image, filePath);
        imageService.updateAdImage(id, image, filePath);

//        adEntity.setImage("/" + photoDir + "/" + adEntity.getId());
//        adRepository.save(adEntity);

    }

    @Override
    public PhotoEntity findPhoto(Integer id) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        return photoRepository.findByAdId(id).get();
    }

    public boolean isAuthorAd(String username, Integer adId) {
        log.info("Использован метод сервиса: {}", LoggingMethodImpl.getMethodName());

        AdEntity adEntity = adRepository.findById(adId).orElseThrow(RuntimeException::new);
        return adEntity.getAuthor().getUserName().equals(username);
    }

}
