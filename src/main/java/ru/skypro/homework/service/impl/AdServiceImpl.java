package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
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
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final PhotoRepository photoRepository;
    private final AdMapper adMapper;

    private final ImageService imageService;

    private final UserService userService;

    @Value("${path.to.photos.folder}")
    private String photoDir;

    public AdServiceImpl(AdRepository adRepository,
                         PhotoRepository photoRepository,
                         AdMapper adMapper,
                         ImageService imageService, UserService userService) {
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
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication) throws IOException {

        AdEntity adEntity = adMapper.mapToAdEntity(properties, userService.getUser(authentication).getUserName());
        adRepository.save(adEntity);

        Path filePath = Path.of(photoDir, adEntity.getId() + "-" + properties.getTitle() + "."
                + imageService.getExtension(image.getOriginalFilename()));

        imageService.saveFileOnDisk(image, filePath);
        imageService.updateAdImage(adEntity, image, filePath);

        adEntity.setImage("/" + photoDir + "/" + adEntity.getId());
        adRepository.save(adEntity);

        return adMapper.mapToAdDto(adEntity);

    }

    @Override
    public ExtendedAd getAds(Integer id) {
        AdEntity entity = adRepository.findById(id).get();
        return adMapper.mapToExtendedAdDto(entity);
    }

    @Override
    public boolean removeAd(Integer id) {
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
        AdEntity entity = adRepository.findById(id).get();

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        adRepository.save(entity);
        return adMapper.mapToAdDto(entity);
    }

    @Override
    public Ads getAdsMe(String username) {
        UserEntity author = userService.checkUserByUsername(username);

        List<Ad> ads = adRepository.findByAuthor(author).stream()
                .map(ad -> adMapper.mapToAdDto(ad))
                .collect(Collectors.toList());

        return new Ads(ads.size(), ads);
    }

    @Override
    public void updateImage(Integer id, MultipartFile image) throws IOException {
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

        AdEntity adEntity = adRepository.findById(id).get();

        // todo продумать путь до файла
        Path filePath = Path.of(photoDir, adEntity.getId() + "."
                + imageService.getExtension(image.getOriginalFilename()));

        imageService.saveFileOnDisk(image, filePath);
        imageService.updateAdImage(adEntity, image, filePath);

        adEntity.setImage("/" + photoDir + "/" + adEntity.getId());
        adRepository.save(adEntity);

    }

    @Override
    public PhotoEntity findPhoto(Integer id) {
        return photoRepository.findByAdId(id).get();
    }

}
