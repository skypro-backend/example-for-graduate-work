package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public Ad addAd(MultipartFile image, CreateOrUpdateAd adDetails) {
        return null;
    }

    @Override
    public List<Ad> getAllAds() {
        return null;
    }

    @Override
    public ExtendedAd getAdById(Long id) {
        return null;
    }

    @Override
    public boolean removeAd(Long id) {
        return false;
    }

    @Override
    public Ad updateAd(Long id, CreateOrUpdateAd adDetails) {
        return null;
    }

    @Override
    public List<Ad> getAdsByCurrentUser() {
        return null;
    }

    @Override
    public byte[] updateAdImage(Long id, MultipartFile image) {
        return new byte[0];
    }
}
