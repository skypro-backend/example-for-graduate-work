package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.validation.PriceValidation;

import java.util.NoSuchElementException;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    private AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final CommentRepository commentRepository;
    private final PriceValidation priceValidation;

    public AdsServiceImpl(AdRepository adRepository,
                          UserRepository userRepository,
                          ImageService imageService,
                          CommentRepository commentRepository,
                          PriceValidation priceValidation) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.commentRepository = commentRepository;
        this.priceValidation = priceValidation;
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
        newAd.setPrice(priceValidation.checkPrice(createOrUpdateAdDto.getPrice()));
        newAd.setDescription(createOrUpdateAdDto.getDescription());
        newAd.setUser(userRepository.findUserByUsername(username).orElseThrow(NoSuchElementException::new));
        newAd.setImage(imageService.uploadImage(image));

        adRepository.save(newAd);
        return AdDto.fromAd(newAd);
    }

    @Override
    public AdDto getAdById(Integer id) {
        Ad ad = adRepository.findByPk(id).orElseThrow(NoSuchElementException::new);
        return AdDto.fromAd(ad);
    }

    @Override
    public FullAdDto getFullAdById(Integer id) {
        Ad ad = adRepository.findByPk(id).orElseThrow(NoSuchElementException::new);
        return FullAdDto.fromAd(ad);
    }

    @Override
    public void removeAd(Integer id, String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(NoSuchElementException::new);
        Ad ad = adRepository.findByPk(id).orElseThrow(NoSuchElementException::new);

        if (user.equals(ad.getUser()) || user.getRole().equals(Role.ADMIN.toString())) {
            adRepository.deleteById(id);
            imageService.deleteImage(ad.getImage());
            commentRepository.deleteAllByAd_Pk(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }


    }

    @Override
    public boolean updateAdById(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(NoSuchElementException::new);
        Ad oldAd = adRepository.findByPk(id).orElseThrow(NoSuchElementException::new);

        if (user.equals(oldAd.getUser()) || user.getRole().equals(Role.ADMIN.name())) {
            oldAd.setTitle(createOrUpdateAdDto.getTitle());
            oldAd.setPrice(createOrUpdateAdDto.getPrice());
            oldAd.setDescription(createOrUpdateAdDto.getDescription());
            oldAd.setUser(oldAd.getUser());
            adRepository.save(oldAd);
        }
        return true;
    }

    @Override
    public AdsDto getAllAdsForUser(String userName) {
        List<Ad> userAdList = adRepository.findAdsByUser_UsernameContains(userName).orElseThrow(NoSuchElementException::new);
        return new AdsDto().fromAdsList(userAdList);
    }

    @Override
    public boolean updateImageById(Integer id,  MultipartFile image) {
        Ad oldAd = adRepository.findByPk(id).orElseThrow(NoSuchElementException::new);
        String imageId = imageService.uploadImage(image);
        oldAd.setImage(imageId);
        adRepository.save(oldAd);
        return true;
    }
}
