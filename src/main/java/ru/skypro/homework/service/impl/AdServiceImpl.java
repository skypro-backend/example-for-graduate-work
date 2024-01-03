package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AccessNotException;
import ru.skypro.homework.exception.AdNotFoundException;
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
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final ImageService imageService;
    private final UserService userService;

    @Override
    public AdsDto getAll() {
        List<AdDto> allAds = adRepository.findAll().stream()
                .map(AdMapper::mapToAdDto)
                .collect(Collectors.toList());
        return new AdsDto(allAds.size(), allAds);
    }
    @Override
    public AdDto createAd(CreateOrUpdateAdDto AdDto, MultipartFile image, Authentication authentication) throws IOException {
        if (authentication.isAuthenticated()) {
            Image newImage = imageService.saveToDataBase(image);
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
    public ExtendedAdDto getAdById(int id) {
        return null;
    }
    @Override
    public void deleteAd(int id) {
    }

    @Override
    public AdDto updateAd(int id, CreateOrUpdateAdDto adDto) {
        return null;
    }

    @Override
    public AdsDto getMyAds(Authentication authentication) {
        return null;
    }
    @Override
    public void updateAdImage(Integer id, MultipartFile image, Authentication authentication) {
        String adAuthorName = adRepository.findById(id).orElseThrow(AdNotFoundException::new).getAuthor().getEmail();
        if (ValidationServiceImpl.isAdmin(authentication) || ValidationServiceImpl.isOwner(authentication, adAuthorName)) {
            Ad ad = adRepository.findById(id).orElseThrow(AdNotFoundException::new);
            imageService.deleteImage(ad.getImage());
            try {
                Image newImage = imageService.saveToDataBase(image);
                ad.setImage(newImage);
                ad.setImageUrl("/images/" + newImage.getId());
                adRepository.save(ad);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        } else {
            throw new AccessNotException();
        }
    }
}
