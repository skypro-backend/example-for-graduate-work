package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;

import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final SecurityCheck securityCheck;

    @Override
    public List<AdDto> getAllAds() {
        log.info("Был вызван метод для получения всех объявлений");
        return adMapper.toAdsDto(adRepository.findAll());
    }

    @Override
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        log.info("Был вызван метод для создания объявления");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User currentUser = userRepository.findByEmail(username).orElseThrow();

        Ad ad = adMapper.toAdCreate(createOrUpdateAdDto);
        Image imageDb = null;
        try {
            imageDb = new Image();
            imageDb.setData(image.getBytes());
            imageDb.setFileSize(image.getSize());
            imageRepository.save(imageDb);
        } catch (IOException e) {
            log.error("error while uploading image", e);
        }

        ad.setImage(imageDb);
        ad.setAuthor(currentUser);
        return adMapper.toAdDto(adRepository.save(ad));
    }

    @Override
    public ExtendedAdDto getAdInformation(Integer id) {
        log.info("Был вызван метод для получения информации об объявлении по идентификатору");
        Ad ad = adRepository.findById(id).orElseThrow();
        return adMapper.toExtendedAdDto(ad);
    }

    @Override
    public void deleteAd(Integer id, Authentication authentication) {
        log.info("Был вызван метод для удаления объявления по идентификатору");
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad ad = adRepository.findById(id).orElseThrow();
        securityCheck.checkAd(id);
        if (securityCheck.isAdmin(user) || securityCheck.isAuthorAd(user, ad)) {
            commentRepository.deleteByAdId(id);
            if (ad.getImage() != null) {
                imageRepository.deleteById(ad.getImage().getId());
            }
            adRepository.deleteById(id);
        }
    }

    @Override
    public AdDto updateAdInformation(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto,
                                     Authentication authentication) {
        log.info("Был вызван метод для обновления объявления по идентификатору");
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad ad = adRepository.findById(id).orElseThrow();
        securityCheck.checkAd(id);

        if (securityCheck.isAdmin(user) || securityCheck.isAuthorAd(user, ad)) {
            ad.setDescription(createOrUpdateAdDto.getDescription());
            ad.setTitle(createOrUpdateAdDto.getTitle());
            ad.setPrice(createOrUpdateAdDto.getPrice());
        }
        return adMapper.toAdDto(adRepository.save(ad));
    }


    public List<AdDto> getAuthorizedUserAds(Authentication authentication) {
        log.info("Был вызван метод для получения объявлений авторизованного пользователя");
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        return adMapper.toAdsDto(adRepository.findAllByAuthorId(user.getId()));
    }

    @Override
    public void updateImageAd(Integer adsId, MultipartFile imageFile,
                              Authentication authentication) throws Exception {
        log.info("завершено обновление изображения объявления");
        User userAd = userRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad adImage = adRepository.findById(adsId).orElseThrow();
        if (securityCheck.isAdmin(userAd) || securityCheck.isAuthorAd(userAd, adImage)) {
            Image imageAd = imageRepository.findById(adImage.getImage().getId()).orElseThrow();
            imageAd.setData(imageFile.getBytes());
            imageAd.setFileSize(imageFile.getSize());
            imageRepository.save(imageAd);
            return;
        }
        throw new RuntimeException();
    }

}
