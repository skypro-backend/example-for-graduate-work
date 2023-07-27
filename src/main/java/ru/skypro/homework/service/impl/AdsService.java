package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsImageRepository;
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
@RequiredArgsConstructor
public class AdsService {

    @Value("${path.to.ad.images}/")
    private String pathToAdImages;
    private final AdsRepository adsRepository;
    private final AdMapperService adMapperService;
    private final UserService userService;
    private final AdsImageRepository adsImageRepository;
    private final ImageService imageService;




      public AdsDto getAllAds() {
        List<Ad> allAds = adsRepository.findAll();
        return new AdsDto(allAds.size(), adMapperService.adListToAdDTOList(allAds));
    }

    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile file) {
        User user = userService.getUser();
        Ad ads = new Ad();
        ads.setUser(user);
        ads.setDescription(properties.getDescription());
        ads.setImageAddress("0");
        ads.setPrice(properties.getPrice());
        ads.setTitle(properties.getTitle());
adsRepository.save(ads);

        ads.setImageAddress(adsImageRepository.findAdsImageByImageAddress(imageService.updateAdImage(ads.getPk(), file)));
        return adMapperService.mapToDto(adsRepository.save(ads));
    }

    public ExtendedAdDto getAds(Integer id) {
        return adMapperService.mapToExtendedDto(adsRepository.findById(id).orElseThrow());
    }

    public void removeAd(Integer id) {
        User user = userService.getUser();
        Ad ad = adsRepository.findById(id).orElseThrow();
        if (user.getId().equals(ad.getUser().getId()) || user.getRole() == Role.ADMIN) {
            adsRepository.delete(ad);
        }
    }

    public AdDto updateAds(Integer id, CreateOrUpdateAdDto newAds) {
        User user = userService.getUser();
        Ad ad = adsRepository.findById(id).orElseThrow();
        if (user.getId().equals(ad.getUser().getId()) || user.getRole() == Role.ADMIN) {
            ad.setTitle(newAds.getTitle());
            ad.setPrice(newAds.getPrice());
            ad.setDescription(newAds.getDescription());

        }
        return adMapperService.mapToDto(adsRepository.save(ad));
    }

    public AdsDto getAdsMe() throws UserNotFoundException {
        User user = userService.getUser();

        List<Ad> myAds = adsRepository.findAll().stream()
                .filter(ad -> ad.getUser().getId() == user.getId())
                .collect(Collectors.toList());

        return new AdsDto(myAds.size(),
                adMapperService.adListToAdDTOList(myAds));
    }

        public AdDto updateImage(Integer id, MultipartFile image) {
            User user = userService.getUser();
            Ad ad = adsRepository.findById(id).orElseThrow();
            if (user.getId().equals(ad.getUser().getId()) || user.getRole() == Role.ADMIN){
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
