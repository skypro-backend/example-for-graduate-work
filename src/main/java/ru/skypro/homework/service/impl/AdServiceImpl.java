package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.NoAccessToAdException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.mapping.AdMapper;

import java.io.IOException;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageRepository imageRepository;

    private final CommentRepository commentRepository;

    public AdServiceImpl(UserRepository userRepository, AdRepository adRepository, AdMapper adMapper, ImageRepository imageRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.imageRepository = imageRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Ad> getAllAdsFromDatabase(String userName) {
        List<AdEntity> allAdsFromDbCollect = adRepository.findAllByUserRelated(userRepository.findByUsername(userName).getId());
        return adMapper.adEntityToAdsDto(allAdsFromDbCollect);
    }

    @Override
    public Ads allAdsPassToController() {
        Ads ads = new Ads();
        List<Ad> adList = adMapper.adEntityToAdsDto(adRepository.findAll());
        ads.setResults(adList);
        ads.setCount(adList.size());
        return ads;
    }

    @Transactional
    @Override
    public AdEntity newAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username) {

        AdEntity adEntity = adMapper.createOrUpdateAdDtoToAdEntity(createOrUpdateAd);
        adEntity.setUserRelated(userRepository.findByUsername(username));

        try {
            byte[] imageToBytes = image.getBytes();
            Image multipartToEntity = new Image();
            multipartToEntity.setImage(imageToBytes);
            imageRepository.save(multipartToEntity);
            adEntity.setImageAd(multipartToEntity);
            adRepository.saveAndFlush(adEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return adEntity;
    }

    @Override
    public ExtendedAd requestAdFromDbById(int id) {
        return adMapper.adEntityToExtendedAdDto(adRepository.getReferenceById(id));
    }

    @Override
    public AdEntity editPatch(CreateOrUpdateAd createOrUpdateAd, int id, String username) {
        AdEntity adFoundToPatch = adRepository.findById(id);
        UserEntity userPostedAd = adRepository.findById(id).getUserRelated();
        UserEntity authorizedUser = userRepository.findByUsername(username);
        Role authorizedUserRole = authorizedUser.getRole();

        if (userPostedAd.equals(authorizedUser) || authorizedUserRole == Role.ADMIN) {
            adFoundToPatch.setTitle(createOrUpdateAd.getTitle());
            adFoundToPatch.setPrice(createOrUpdateAd.getPrice());
            adFoundToPatch.setDescription(createOrUpdateAd.getDescription());
            adRepository.save(adFoundToPatch);
            return adFoundToPatch;
        } else {
            throw new NoAccessToAdException();
        }
    }

    @Override
    public Ads authorizedUserAds(String username) {
        UserEntity authorizedUser = userRepository.findByUsername(username);
        return adMapper.userAdsToAdsDto(authorizedUser);
    }

    @Override
    public boolean patchAdPictureById(MultipartFile image, int adId, String username) {
        AdEntity adToModify = adRepository.findById(adId);
        UserEntity userPostedAd = adRepository.findById(adId).getUserRelated();
        UserEntity authorizedUser = userRepository.findByUsername(username);
        Role authorizedUserRole = authorizedUser.getRole();

        Image multipartToImage = new Image();

        try {

            byte[] imageToBytes = image.getBytes();
            multipartToImage.setImage(imageToBytes);
            imageRepository.save(multipartToImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if ((userPostedAd.equals(authorizedUser)) || authorizedUserRole == Role.ADMIN) {

            adToModify.setImageAd(multipartToImage);
            adRepository.save(adToModify);
            return true;
        }
        return false;
    }


    @Transactional
    @Override
    public boolean deleteAdById(int id, String username) {
        UserEntity userPostedAd = adRepository.findById(id).getUserRelated();
        UserEntity authorizedUser = userRepository.findByUsername(username);
        Role authorizedUserRole = authorizedUser.getRole();

        if ((userPostedAd.equals(authorizedUser)) || authorizedUserRole == Role.ADMIN) {
            adRepository.deleteById(id);
            commentRepository.deleteById(id);
            adRepository.flush();
            return true;
        } else {
            throw new NoAccessToAdException();
        }
    }

    @Override
    public AdEntity callAdById(int id) {
        return adRepository.getReferenceById(id);
    }
}
