package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exceptions.EmptyException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.AdRepo;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;

    private final AdRepo adRepo;

    private final UserRepo userRepo;

    private final ImageService imageService;

    private final Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);
    ;

    @Override
    public AdsDTO findAll() {
        logger.info("AdService findAll is running");
        return adMapper.convertToAdsDTO(adRepo.findAll());
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile imageFile) throws IOException {
        logger.info("AdService createAd is running");
        Ad ad = adMapper.convertCreatDTOToAd(createOrUpdateAdDTO);
        ad.setAuthor(getAuthUser());
        Ad savedAd = adRepo.save(ad);
        Image image = imageService.saveImageToUser(imageFile);
        ad.setImage(savedAd.getImage());
        imageService.saveImageToDb(image);
        savedAd.setImage(image);
        adRepo.save(savedAd);
        return adMapper.convertToAdDTO(savedAd);
    }

    @Override
    public ExtendedAdDTO findById(Long id) {
        logger.error("AdService findById is running");
        return adRepo.findById(Math.toIntExact(id))
                .map(adMapper::convertToExtendedAd)
                .orElseThrow(() -> new EmptyException("Ad not found"));
    }

    @Override
    public AdsDTO getAdByAuthUser() {
        User user = getAuthUser();
        return adMapper.convertToAdsDTO(adRepo.findAllByUserId(Math.toIntExact(user.getId())));

    }

    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new EmptyException("User with email: " + email + " is not found"));
    }


    @Override
    public AdDTO updateAd(Long id, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = adRepo.findById(Math.toIntExact(id)).orElseThrow(() -> new EmptyException("Ad not found"));
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        ad.setTitle(createOrUpdateAdDTO.getTitle());
        return adMapper.convertToAdDTO(ad);
    }

    @Override
    public void updateImage(int id, MultipartFile imageFile) throws IOException {
        imageService.updateImage(imageFile, id);
    }

    @Override
    public void deleteAd(int id) {
        adRepo.findById(id)
                .orElseThrow(() -> new EmptyException("Ad not found"));
        adRepo.deleteById(id);
    }
}
