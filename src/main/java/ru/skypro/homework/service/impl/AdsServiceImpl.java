package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.mapper.AdsMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final ImageRepository imageRepository;
    private final AdsMapper adsMapper;
    private final AuthServiceImpl authService;
    private final ImageService imageService;
    @Override
    @Transactional
    public AllAdsDTO getAllAds() {
        List<AdsDTO> adsDTOList = adsRepository.findAll().stream()
                .map(adsMapper ::adsToAdsDto)
                .collect(Collectors.toList());

        AllAdsDTO allAdsDTO = new AllAdsDTO();
        allAdsDTO.setResults(adsDTOList);
        allAdsDTO.setCount(adsDTOList.size());

        return allAdsDTO;
    }

    @Override
    @Transactional
    public AdsDTO addAds(MultipartFile image, CreateAdsDTO properties) {
        UserInfo user = authService.getCurrentUser();

        Image uploadImage = imageService.uploadImage(image);
        imageRepository.save(uploadImage);
        Ads ads = adsMapper.createAdsDtoToModel(properties);
        ads.setImage(uploadImage);
        ads.setAuthor(user);
        adsRepository.save(ads);

        return adsMapper.adsToAdsDto(ads);
    }

    @Override
    @Transactional
    public AdsInfoDTO getAdsById(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);

        return adsMapper.adsToAdsInfoDto(ads);
    }

    @Override
    @Transactional
    public void deleteAds(Integer id) {
        UserInfo user = authService.getCurrentUser();
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        if (user.getId().equals(ads.getAuthor().getId()) || user.getRole().equals(Role.ADMIN)) {
            adsRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public AdsDTO updateAds(Integer id, CreateAdsDTO createAdsDTO) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        UserInfo user = authService.getCurrentUser();
        if (user.getId().equals(ads.getAuthor().getId()) || user.getRole().equals(Role.ADMIN)) {
            ads.setDescription(createAdsDTO.getDescription());
            ads.setPrice(createAdsDTO.getPrice());
            ads.setTitle(createAdsDTO.getTitle());

        }

        return adsMapper.adsToAdsDto(ads);
    }

    @Override
    @Transactional
    public AllAdsDTO getUserAds() {
        UserInfo user = authService.getCurrentUser();
        List<AdsDTO> adsDTOList = user.getAds().stream()
                .map(adsMapper::adsToAdsDto)
                .collect(Collectors.toList());

        AllAdsDTO allAdsDTO = new AllAdsDTO();
        allAdsDTO.setResults(adsDTOList);
        allAdsDTO.setCount(adsDTOList.size());

        return allAdsDTO;
    }

    @Override
    @Transactional
    public void updateAdsImage(Integer id, MultipartFile image) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        UserInfo user = authService.getCurrentUser();
        Image uploadImage = imageService.uploadImage(image);
        if (user.getId().equals(ads.getAuthor().getId()) || user.getRole().equals(Role.ADMIN)) {
            ads.setImage(uploadImage);
            adsRepository.save(ads);
        }
    }

    @Override
    @Transactional
    public byte[] getImage(String id) {
        return imageService.getImage(id);
    }
}
