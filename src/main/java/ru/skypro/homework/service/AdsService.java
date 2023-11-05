package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.utils.MyMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdsService {
    private static Logger logger = LoggerFactory.getLogger(AdsService.class);
    private final MyMapper mapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    @Value("${ads.image.dir.path}")
    private String imageDir;

    public AdDto addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) throws IOException {

        UserEntity userEntity = userRepository.findByUsername("username1");

        Path filePath = Path.of(imageDir, UUID.randomUUID().toString() + "." + getExtension(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream inputStream = image.getInputStream();
             OutputStream outputStream = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024);
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdEntity adEntity = adRepository.save(mapper.map(createOrUpdateAd, userEntity, filePath.toString()));
        logger.info("Ad was successfully added: {}", createOrUpdateAd.getTitle());

        return mapper.map(adEntity);
    }

    public Ads getAllAds() {
        List<AdEntity> entities = adRepository.findAll();
        logger.info("Number of ads sent: {}", entities.size());
        return mapper.map(entities);
    }

    public AdInfoDto getAdInfo(Integer id) {
        AdEntity adEntity = adRepository.getReferenceById((long) id);
        if (adEntity == null) {
            return null;
        }
        return mapper.map(adEntity,true);
    }

    public AdEntity deleteAd(Integer id) {
        AdEntity adEntity = adRepository.getReferenceById((long) id);
        if (adEntity == null) {
            return null;
        }
        adRepository.deleteById((long) id);
        return adEntity;
    }

    public AdDto updateImage(Integer id, MultipartFile image) throws IOException {
        AdEntity adEntity = adRepository.getReferenceById((long) id);
        if (adEntity == null) {
            return null;
        }

        Path filePath = Path.of(imageDir, UUID.randomUUID().toString() + "." + getExtension(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream inputStream = image.getInputStream();
             OutputStream outputStream = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024);
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path = adEntity.getImage();
        if (path != null) {
            File file = new File(path);
            file.delete();
        }

        adEntity.setImage(filePath.toString());
        adRepository.save(adEntity);
        logger.info("Image updated successfully: {}", adEntity.getImage());

        return mapper.map(adEntity);
    }

    public AdDto patchAd(Integer id, AdUpdateDto adUpdateDto) {
        AdEntity adEntity = adRepository.getReferenceById((long) id);
        if (adEntity == null) {
            return null;
        }
        adEntity.setTitle(adUpdateDto.getTitle());
        adEntity.setPrice(adUpdateDto.getPrice());
        adEntity.setDescription(adUpdateDto.getDescription());
        adRepository.save(adEntity);
        logger.info("Ad updated successfully: {}", adEntity.getTitle());
        return mapper.map(adEntity);
    }

    public Ads getAdsMe() {
        String username = "Get from authority";
        UserEntity userEntity = userRepository.findByUsername(username);
        List<AdEntity> entities = adRepository.findAllByAuthor(userEntity.getId());
        logger.info("Number of ads sent: {}", entities.size());
        return mapper.map(entities);

    }

    private String getExtension(String fileName) {
        logger.debug("Method getExtension is called, argument(s) passed: {}", fileName);
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
