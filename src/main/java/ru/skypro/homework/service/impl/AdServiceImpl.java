package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.security.CustomUserDetails;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.util.SecurityUtil;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final ImageService imageService;

    @Autowired
    public AdServiceImpl(AdRepository adRepository, ImageService imageService) {
        this.adRepository = adRepository;
        this.imageService = imageService;
    }

    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public Ad getAdById(Long id) {
        return adRepository.findById(id).orElseThrow(() -> new RuntimeException("Ad not found with id: " + id));
    }

    @Override
    public Ad createAd(CreateOrUpdateAd dto, MultipartFile imageFile) {
        Ad ad = new Ad();
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setPrice(dto.getPrice());
        ad.setImage(imageService.saveImage(imageFile));
        ad.setAuthor(SecurityUtil.getUserDetails().getUser());
        return adRepository.save(ad);
    }

    @Override
    public Ad updateAd(Long id, CreateOrUpdateAd dto) {
        Ad ad = getAdById(id);
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setPrice(dto.getPrice());
        return adRepository.save(ad);
    }

    @Override
    public void deleteAd(Long id) {
        if (!adRepository.existsById(id)) {
            throw new RuntimeException("Ad not found with id: " + id);
        }
        adRepository.deleteById(id);
    }

    @Override
    public List<Ad> getMyAds() {
        CustomUserDetails userDetails = SecurityUtil.getUserDetails();
        User user = userDetails.getUser();
        return user.getAds();
    }

    @Override
    public Ad updateAdImage(Long id, MultipartFile imageFile) {
        Ad ad = getAdById(id);
        ad.setImage(imageService.saveImage(imageFile));
        return adRepository.save(ad);
    }
}
