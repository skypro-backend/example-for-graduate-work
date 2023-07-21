package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdMapperService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsService {

    @Value("${path.to.ad.images}/")
    private String pathToAdImages;
    private final AdsRepository adsRepository;
    private final AdMapperService adMapperService;
    private final UserService userService;
    private final AdImageRepository adImageRepository;
    private final ImageService imageService;

    public AdsService(AdsRepository adsRepository, AdMapperService adMapperService, UserService userService, AdImageRepository adImageRepository, ImageService imageService) {
        this.adsRepository = adsRepository;
        this.adMapperService = adMapperService;
        this.userService = userService;
        this.adImageRepository = adImageRepository;
        this.imageService = imageService;
    }

    public Object getAllAds() {
        List<Ad> allAds = adsRepository.findAll();
        return allAds.stream().map(e -> adMapperService.mapToDto(e)).collect(Collectors.toList());
    }

    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile file) {
        User user = userService.getUser();
        if (user == null) {
            throw new UserNotFoundException("Такой пользователь не существует");
        }
        Ad ad = new Ad(user, properties.getDescription(), null, properties.getPrice(), properties.getTitle());
        Ad savedAd = adsRepository.save(ad);

        savedAd.setImageAddress(adImageRepository.findAdImageByImageAddress(imageService.updateAdImage(savedAd.getPk(), file)));

        return adMapperService.mapToDto(adsRepository.save(savedAd));
    }

    public ExtendedAdDto getAds(Integer id) {
        Ad ad = adsRepository.findById(id).orElseThrow();
        return adMapperService.mapToExtendedDto(ad);
    }

    public void removeAd(Integer id) {
        User user = userService.getUser();
        Ad ad = adsRepository.findById(id).orElseThrow();
        if (user.getUserId().equals(ad.getUser().getUserId()) || user.getRoleDto() == RoleDto.ADMIN) {
            adsRepository.delete(ad);
        }
    }

    public AdDto updateAds(Integer id, CreateOrUpdateAdDto newAds) {
        User user = userService.getUser();
        Ad ad = adsRepository.findById(id).orElseThrow();
        if (user.getUserId().equals(ad.getUser().getUserId()) || user.getRoleDto() == RoleDto.ADMIN) {
            ad.setTitle(newAds.getTitle());
            ad.setPrice(newAds.getPrice());
            ad.setDescription(newAds.getDescription());

        }
        return adMapperService.mapToDto(adsRepository.save(ad));
    }

        public AdsDto getAdsAllUser() {
            User user = userService.getUser();
            List<AdDto> allAdsUser = adsRepository.findAdsByUser(user).stream().collect(Collectors.toList());
            return new  AdsDto(allAdsUser);
        }

        public AdDto updateImage(Integer id, MultipartFile image) {
            User user = userService.getUser();
            Ad ad = adsRepository.findById(id).orElseThrow();
            if (user.getUserId().equals(ad.getUser().getUserId()) || user.getRoleDto() == RoleDto.ADMIN){
                File tempFile = new File(pathToAdImages, ad.getPk() + "_ad_image.jpg");
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(image.getBytes());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Файл отстутсвует или не найден");
                } catch (IOException e) {
                    throw new RuntimeException();
                }}
            return adMapperService.mapToDto(ad);

        }
    }
