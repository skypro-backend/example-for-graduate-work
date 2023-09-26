package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.util.NoSuchElementException;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    private AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public AdsServiceImpl(AdRepository adRepository, UserRepository userRepository, ImageService imageService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Override
    public AdsDto getAllAds() {
        List<Ad> adList = adRepository.findAll();
        return new AdsDto().fromAdsList(adList);
    }


    @Override
    public AdDto createAds(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        Ad newAd = new Ad();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        newAd.setTitle(createOrUpdateAdDto.getTitle());
        newAd.setPrice(createOrUpdateAdDto.getPrice());
        newAd.setDescription(createOrUpdateAdDto.getDescription());
        newAd.setUser(userRepository.findUserByUsername(username).orElseThrow(NoSuchElementException::new));
        newAd.setImage(imageService.uploadImage(image));
        adRepository.save(newAd);
        return AdDto.fromAd(newAd);
    }

    @Override
    public AdDto getAdById(Integer id) {
        return AdDto.fromAd(adRepository.findByPk(id));
    }

    @Override
    public void removeAd(Integer id) {

    }

    @Override
    public AdDto updateAdById(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad oldAd = adRepository.findByPk(id);
        oldAd.setTitle(createOrUpdateAdDto.getTitle());
        oldAd.setPrice(createOrUpdateAdDto.getPrice());
        oldAd.setDescription(createOrUpdateAdDto.getDescription());
        oldAd.setUser(oldAd.getUser());
        adRepository.save(oldAd);
        return AdDto.fromAd(oldAd);
    }

    @Override
    public AdsDto getAllAdsForUser(String userName) {
        List<Ad> userAdList = adRepository.findAdsByUser_UsernameContains(userName);
        return new AdsDto().fromAdsList(userAdList);
    }

    @Override
    public AdDto updateImageById(Integer id, String image) {
        Ad oldAd = adRepository.findByPk(id);
        oldAd.setImage(image);
        adRepository.save(oldAd);
        return AdDto.fromAd(oldAd);
    }
}
