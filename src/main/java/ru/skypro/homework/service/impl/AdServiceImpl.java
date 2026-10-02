package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.api.dto.Ad;
import ru.skypro.homework.api.dto.Ads;
import ru.skypro.homework.api.dto.CreateOrUpdateAd;
import ru.skypro.homework.api.dto.ExtendedAd;
import ru.skypro.homework.db.model.AdModel;
import ru.skypro.homework.db.model.UserModel;
import ru.skypro.homework.db.model.UserRole;
import ru.skypro.homework.db.repository.AdRepository;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageStorageService;
import ru.skypro.homework.service.StoredImage;
import ru.skypro.homework.service.UserService;

import org.springframework.security.access.AccessDeniedException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private static final String AD_IMAGE_PATH = "/ads/%d/image";

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageStorageService imageStorageService;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public Ads getAllAds() {
        return adMapper.toAds(adRepository.findAll());
    }

    @Override
    @Transactional
    public Ad addAd(String username, CreateOrUpdateAd properties, MultipartFile image) {
        UserModel author = userService.getCurrentUserModel(username);
        AdModel ad = adMapper.toModel(properties);
        ad.setAuthor(author);
        ad.setImage(String.format(AD_IMAGE_PATH, 0));
        ad = adRepository.save(ad);

        String imageFileName = imageStorageService.save(image);
        ad.setImage(buildImagePath(ad.getId(), imageFileName));
        return adMapper.toDto(adRepository.save(ad));
    }

    @Override
    @Transactional(readOnly = true)
    public Ads getCurrentUserAds(String username) {
        UserModel author = userService.getCurrentUserModel(username);
        return adMapper.toAds(adRepository.findAllByAuthor(author));
    }

    @Override
    @Transactional(readOnly = true)
    public ExtendedAd getAd(Integer id) {
        return adMapper.toExtendedDto(getAdModel(id));
    }

    @Override
    @Transactional
    public Ad updateAd(String username, Integer id, CreateOrUpdateAd ad) {
        UserModel currentUser = userService.getCurrentUserModel(username);
        AdModel adModel = getAdModel(id);
        checkCanModify(currentUser, adModel);

        adModel.setTitle(ad.getTitle());
        adModel.setPrice(ad.getPrice());
        adModel.setDescription(ad.getDescription());
        return adMapper.toDto(adRepository.save(adModel));
    }

    @Override
    @Transactional
    public void deleteAd(String username, Integer id) {
        UserModel currentUser = userService.getCurrentUserModel(username);
        AdModel adModel = getAdModel(id);
        checkCanModify(currentUser, adModel);
        adRepository.delete(adModel);
    }

    @Override
    @Transactional(readOnly = true)
    public StoredImage getImage(Integer id) {
        AdModel ad = getAdModel(id);
        return imageStorageService.read(getImageFileName(ad));
    }

    @Override
    @Transactional
    public StoredImage updateImage(String username, Integer id, MultipartFile image) {
        UserModel currentUser = userService.getCurrentUserModel(username);
        AdModel ad = getAdModel(id);
        checkCanModify(currentUser, ad);

        String imageFileName = imageStorageService.save(image);
        ad.setImage(buildImagePath(ad.getId(), imageFileName));
        adRepository.save(ad);
        return imageStorageService.read(imageFileName);
    }

    private AdModel getAdModel(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    private void checkCanModify(UserModel currentUser, AdModel ad) {
        boolean isAdmin = currentUser.getRole() == UserRole.ADMIN;
        boolean isAuthor = ad.getAuthor().getId().equals(currentUser.getId());
        if (!isAdmin && !isAuthor) {
            throw new AccessDeniedException("Only author or admin can modify ad");
        }
    }

    private String buildImagePath(Integer adId, String imageFileName) {
        return String.format(AD_IMAGE_PATH, adId) + "?file=" + imageFileName;
    }

    private String getImageFileName(AdModel ad) {
        String imagePath = ad.getImage();
        int fileNameStart = imagePath.indexOf("?file=");
        if (fileNameStart < 0) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return imagePath.substring(fileNameStart + "?file=".length());
    }
}
