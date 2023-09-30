package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdServiceImpl extends AdService {

    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;

    @Override
    public Ad createAd(String userLogin, MultipartFile multipartFile, CreateOrUpdateAd createAd) {
        Optional<ru.skypro.homework.dto.User> user = userService.getUserByLogin();
        if (user.isEmpty()) {
            throw new RuntimeException("NOT FOUND");
        }
        Ad ad = new Ad();
        ad = AdMapper.INSTANCE.createOrUpdateAdToAd(createAd, ad);
        ad.setAuthor(user.get().getId());
        adRepository.save(ad);
        Image image;
        try {
            image = imageService.changeAdImage(ad.getId(), -1L, multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ad.setImage(String.valueOf(image));
        adRepository.save(ad);
        return AdMapper.INSTANCE.adToDto(ad);
    }

    @Override
    public List<Ad> getAllAds() {
        List<Ad> adDtoList = new ArrayList<>();
        List<Ad> adList = adRepository.findAll();
        if (adList.isEmpty()) {
            return adList;
        }
        for (Ad ad : adList) {
            adDtoList.add(AdMapper.INSTANCE.adToDto(ad));
        }
        return adDtoList;
    }

    @Override
    public List<Ad> getMyAds(String userLogin) {
        Optional<User> optionalUser = userService.getUserByLogin();
        List<Ad> adList;
        List<Ad> adsList = new ArrayList<>();
        if (optionalUser.isEmpty()) {
            return new ArrayList<>();
        }
        adList = optionalUser.get().getUserAd();
        for (Ad ad : adList) {
            adsList.add(AdMapper.INSTANCE.adToDto(ad));
        }
        return adsList;
    }

    @Override
    public Optional<ExtendedAd> getExtendedAdDto(Long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        return optionalAd.map(AdMapper.INSTANCE::extendedAdToDto);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @permissionCheckServiceImpl.checkAdsUserName(#userLogin, #idAd)")
    public Optional<Ad> updateAd(String userLogin, Long idAd, CreateOrUpdateAd updateAd) {
        Optional<Ad> optionalAd = adRepository.findById(idAd);
        if (optionalAd.isEmpty()) {
            return Optional.empty();
        }
        Ad ad = optionalAd.get();
        ad = AdMapper.INSTANCE.createOrUpdateAdToAd(updateAd, ad);
        adRepository.save(ad);
        return Optional.ofNullable(AdMapper.INSTANCE.INSTANCE.adToDto(ad));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @permissionCheckServiceImpl.checkAdsUserName(authenticated.name, #idAd)")
    public Optional<String> changeImage(Long idAd, MultipartFile multipartFile) {
        Optional<Ad> optionalAd = adRepository.findById(idAd);
        if (optionalAd.isEmpty()) {
            return Optional.empty();
        }
        Ad ad = optionalAd.get();
        Image image;
        try {
            image = imageService.changeAdImage(idAd,
                    ad.getId(),                           //ad.getImage().getId(),
                    multipartFile);
            ad.setImage(String.valueOf(image));
            adRepository.save(ad);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(image.getFileName());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @permissionCheckServiceImpl.checkAdsUserName(#Login, #idAd)")
    public boolean deleteByIdAd(String userLogin, Long idAd) {
        Optional<ru.skypro.homework.dto.User> optionalUser = userService.getUserByLogin();
        if (optionalUser.isEmpty()) {
            return false;
        }
        if (!adRepository.existsById(idAd)) {
            return false;
        } else {
            adRepository.deleteById(idAd);
            return true;
        }
    }

}