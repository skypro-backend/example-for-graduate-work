package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final UserService userService;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final ImageService imageService;

    @Override
    public AdsDTO getAllAds() {
        Collection<AdDTO> allAds = adMapper.mapAdListToAdDTOList(adRepository.findAll());
        return new AdsDTO(allAds);
    }

    @Override
    public AdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image) {
        Ad newAd = adMapper.mapCreateOrUpdateAdDTOtoAd(createOrUpdateAdDTO);
        newAd.setAuthor(userService.findAuthUser().orElseThrow());
        Image newImage = imageService.createImage(image);
        newAd.setImage(newImage);
        adRepository.save(newAd);
        return adMapper.mapAdToAdDTO(newAd);
    }

    @Override
    public ExtendedAdDTO getFullAd(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        return adMapper.mapAdToExtendedAdDTO(ad);
    }

    @Override
    public boolean removeAd(Integer id) {
        if (checkAccess(id)) {
            adRepository.deleteById(id);
            return true;
        }
        throw new AdNotFoundException();
    }

    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = adRepository.findById(id).orElseThrow();
        if (checkAccess(id)) {
            ad.setTitle(createOrUpdateAdDTO.getTitle());
            ad.setDescription(createOrUpdateAdDTO.getDescription());
            ad.setPrice(createOrUpdateAdDTO.getPrice());
            adRepository.save(ad);
            return adMapper.mapAdToAdDTO(ad);
        }
        throw new AdNotFoundException();
    }

    @Override
    public AdsDTO getAllUserAd() {
        User user = userService.findAuthUser().orElseThrow();
        Collection<Ad> allAds = adRepository.findAll();
        Collection<Ad> adsByUser = allAds.stream()
                .filter(u -> u.getAuthor().equals(user))
                .collect(Collectors.toList());
        return new AdsDTO(adMapper.mapAdListToAdDTOList(adsByUser));
    }

    @Override
    public void updateImageAd(Integer id, MultipartFile image) {
        Ad ad = adRepository.findById(id).orElseThrow();
        Image newImage = imageService.updateImage(image, ad.getImage());
        ad.setImage(newImage);
        adRepository.save(ad);
    }

    @Override
    public boolean checkAccess(Integer id) {
        Role role = Role.ADMIN;
        Ad ad = adRepository.findById(id).orElseThrow();
        User user = userService.findAuthUser().orElseThrow();
        String currentName = user.getUsername();
        return ad.getAuthor().getUsername().equals(currentName) || user.getAuthorities().contains(role);
    }
    }
