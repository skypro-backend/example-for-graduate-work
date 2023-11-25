package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.exception.UserNotRegisteredException;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.mapper.AdsMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final AuthServiceImpl authService;
    private final ImageService imageService;
    @Override
    public List<AdsDTO> getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        List<AdsDTO> adsDTOList = Collections.singletonList(adsMapper.adsToAdsDto((Ads) adsList));
        for (int i = 0; i < adsList.size(); i++) {
            if (adsList.get(i).getImageModel() != null) {
                adsDTOList.get(i).setImage("/ads/image" + adsList.get(i).getImageModel().getId());
            }
        }
        return adsDTOList;
    }

    @Override
    @Transactional
    public AdsDTO addAds(MultipartFile image, CreateAdsDTO properties) {
        UserInfo user = authService.getCurrentUser();
        if (user == null) {
            throw new UserNotRegisteredException("The user is not registered");
        } else {
            Image uploadImage = imageService.uploadImage(image);
            Ads ads = adsMapper.createAdsDtoToModel(properties);
            ads.setImageModel(uploadImage);
            adsRepository.save(ads);
            return adsMapper.adsToAdsDto(ads);
        }
    }

    @Override
    public AdsInfoDTO getAdsById(long id) {
        Ads ads = adsRepository.findById(id).orElse(null);
        AdsInfoDTO adsInfoDTO = adsMapper.adsToAdsInfoDto(ads);
        if (ads.getImageModel() != null) {
            adsInfoDTO.setImage("/ads/image" + ads.getImageModel().getId());
        }
        return adsInfoDTO;
    }

    @Override
    public void deleteAds(long id) {
        UserInfo user = authService.getCurrentUser();
        Ads ads = adsRepository.findById(id).orElse(null);
        if (user.getId() == ads.getAuthor().getId() || user.getRole().equals(Role.ADMIN)) {
            adsRepository.deleteById(id);
        }
    }

    @Override
    public AdsDTO updateAds(long id, CreateAdsDTO createAdsDTO) {
        Ads ads = adsRepository.findById(id).orElse(null);
        UserInfo user = authService.getCurrentUser();
        if (user.getId() == ads.getAuthor().getId() || user.getRole().equals(Role.ADMIN)) {
            ads.setDescription(createAdsDTO.getDescription());
            ads.setPrice(createAdsDTO.getPrice());
            ads.setTitle(createAdsDTO.getTitle());
            adsRepository.save(ads);
        }

        return adsMapper.adsToAdsDto(ads);
    }

    @Override
    public List<AdsDTO> getUserAds() {
        UserInfo user = authService.getCurrentUser();
        List<Ads> adsList = user.getAds();
        List<AdsDTO> adsDTOList = Collections.singletonList(adsMapper.adsToAdsDto((Ads) adsList));
        if (user == null) {
            throw new UserNotRegisteredException("The user is not registered");
        } else {
            for (int i = 0; i < adsList.size(); i++) {
                if (adsList.get(i).getImageModel() != null) {
                    adsDTOList.get(i).setImage("/ads/image" + adsList.get(i).getImageModel().getId());
                }
            }
        }
        return adsDTOList;
    }

    @Override
    @Transactional
    public void updateAdsImage(long id, MultipartFile image) {
        Ads ads = adsRepository.findById(id).orElse(null);
        UserInfo user = authService.getCurrentUser();
        Image uploadImage = imageService.uploadImage(image);
        if (user.getId() == ads.getAuthor().getId() || user.getRole().equals(Role.ADMIN)) {
            ads.setImageModel(uploadImage);
        }
        adsRepository.save(ads);
    }

    @Override
    @Transactional
    public byte[] getImage(String id) {
        return imageService.getImage(id);
    }


}
