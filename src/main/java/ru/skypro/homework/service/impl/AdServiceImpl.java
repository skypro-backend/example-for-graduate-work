package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AccessNotException;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.mappers.AdMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import ru.skypro.homework.service.ImageService;

@Service
@AllArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final ImageService imageService;
    private final UserService userService;

     @Override
      public AdsDto getAllAds() {
          List<AdDto> allAds = adRepository.findAll().stream()
                  .map(AdMapper::mapToAdDto)
                  .collect(Collectors.toList());
          return AdsDto.builder()
                  .count(allAds.size())
                  .results(allAds)
                  .build();
             }
    @Override
    public AdDto addAd(CreateOrUpdateAdDto AdDto, MultipartFile image, Authentication authentication) throws IOException {
        if (authentication.isAuthenticated()) {
            Image newImage = imageService.uploadImage(image);
            Ad adEntity = AdMapper.createOrUpdateAdFromDto(AdDto);
            adEntity.setAuthor(userService.findByEmail(authentication.getName()));
            adEntity.setImage(newImage);
            adEntity.setImageUrl("/images/" + newImage.getId());
            adRepository.save(adEntity);
            return AdMapper.mapToAdDto(adEntity);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public ExtendedAdDto getAds(int id) {
        return adRepository.findById(id)
                .map(AdMapper::adToExtendedAdDto)
                .orElseThrow(AdNotFoundException::new);
    }

    @Override
    public void removeAd(int id) {
    }

    @Override
    public AdDto updateAds(int id, CreateOrUpdateAdDto adDto) {
        return null;
    }


    @Override
    public void updateImage(Integer id, MultipartFile image, Authentication authentication) {

        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ImageNotFoundException("Объявление с ID" + id + "не найдено"));
        checkPermit(ad, authentication);
        Image imageFile = ad.getImage();
        ad.setImage(imageService.uploadImage(image));
        imageService.removeImage(imageFile);
        adRepository.save(ad);

    }

    @Override
    public AdsDto getMyAds(Authentication authentication) {
        return null;
    }

    public void checkPermit(Ad ad, Authentication authentication) {
        if (!ad.getAuthor().getEmail().equals(authentication.getName())
                && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new AccessNotException("Вы не можете редактировать или удалять чужое объявление");
        }
    }
}

