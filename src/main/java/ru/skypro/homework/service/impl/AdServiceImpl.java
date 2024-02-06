package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.security.MyUserDetails;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.PhotoService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final PhotoService photoService;
    private final UserService userService;
    private final CommentService commentService;
    private final MyUserDetails userDetails;

    @Override
    public Collection<AdDTO> getAllAds(String title) {
        log.info("Request to receive all ads");
        Collection<AdEntity> ads;
        if (!isEmpty(title)) {
            ads = adRepository.findByTitleLikeIgnoreCase(title);
        } else {
            ads = adRepository.findAll();
        }
        return adMapper.adToAdListDto(ads);
    }

    @Override
    public AdDTO createAd(CreateOrUpdateAdDTO createAdDTO, MultipartFile image, Authentication authentication) {
        if (createAdDTO.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        AdEntity ad = adMapper.adDtoToAdEntity(createAdDTO);
        ad.setAuthor(userService.getAuthorizedUser(authentication));
        log.info("Request to create new ad");
        PhotoEntity adImage;
        try {
            adImage = photoService.downloadPhoto(image);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить фото");
        }
        ad.setPhoto(adImage);
        adRepository.save(ad);
        log.info("Save new ad ID:" + ad.getId());

        return adMapper.adEntityToAdDTO(ad);
    }

    @Override
    public ExtendedAdDTO getFullAd(Long adId) {
        log.info("Request to get full info about ad");
        AdEntity ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        return adMapper.adEntityAndUserEntityToExtendedAdDTO(ad);
    }
    @Transactional
    @Override
    public void deleteAd(Long adId) {
        log.info("Request to delete ad by id");
        AdEntity ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        commentService.deleteAllByAdId(adId);
        photoService.deletePhoto(ad.getPhoto().getId());
        adRepository.deleteById(adId);
    }

    @Override
    public AdDTO updateAd(CreateOrUpdateAdDTO createAdDTO, Long adId) {
        log.info("Request to update ad by id");
        if (createAdDTO.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        AdEntity ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        adMapper.updateAd(createAdDTO, ad);
        adRepository.save(ad);

        return adMapper.adEntityToAdDTO(ad);
    }

    @Override
    public Collection<AdDTO> getUserAllAds() {
        log.info("Request to get all user ads");
        Collection<AdEntity> ads;
        log.info(userDetails.getIdUserDto() + "   " + userDetails.getAuthorities() + "   " + userDetails.getUsername());
        ads = adRepository.findAllAdsByAuthorId(userDetails.getIdUserDto());

        return adMapper.adToAdListDto(ads);
    }

    @Override
    public String updatePhoto(Long adId, MultipartFile image) throws IOException {
        log.info("Request to update image");
        AdEntity updateAd = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        long idImage = updateAd.getPhoto().getId();
        updateAd.setPhoto(photoService.downloadPhoto(image));
        photoService.deletePhoto(idImage);
        adRepository.save(updateAd);
        return adMapper.adEntityToAdDTO(updateAd).getImage();
    }

    @Override
    public byte[] getAdImage(Long adId) {
        log.info("Get image of an AD with a ID:" + adId);
        return photoService.getPhoto(adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getPhoto().getId());
    }
}
