package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdMapperService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsService {

    private final AdsRepository adsRepository;
    private final AdMapperService adMapperService;
    private final UserService userService;
    private final ImageService imageService;


    public AdsDto getAllAds() {
        List<Ad> allAds = adsRepository.findAll();
        return new AdsDto(allAds.size(), adMapperService.adListToAdDTOList(allAds));
    }

    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile image) throws IOException {
        User user = userService.getUser();
        Ad ads = new Ad();
        ads.setUser(user);
        ads.setDescription(properties.getDescription());
        ads.setPrice(properties.getPrice());
        ads.setTitle(properties.getTitle());
        ads.setImageAddress(imageService.uploadAdImage(properties.getTitle(),image));
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

    public AdDto updateAds(Integer id, CreateOrUpdateAdDto newAds) throws UserNotFoundException {
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

    public byte[] updateImage(Integer id, MultipartFile image) throws IOException {
        Ad ad = adsRepository.getById(id);
        ad.setImageAddress(imageService.uploadAdImage(ad.getTitle(), image));
        adsRepository.save(ad);
        return image.getBytes();
    }

    public AdsDto searchAds(String query) {
        List <AdDto> adList = adsRepository
                .findAll()
                .stream()
                .filter(ad -> ad.getTitle().equals(query))
                .map(adMapperService::mapToDto)
                .collect(Collectors.toList());
        return new AdsDto(adList.size(), adList);
    }
}

