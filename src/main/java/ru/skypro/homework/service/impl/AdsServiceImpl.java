package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.mapping.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsMapper adsMapper;

    private final AdsRepository adsRepository;

    private final UserRepository userRepository;


    @Override
    public Ads addAds(CreateAds properties, MultipartFile image, Authentication authentication) {
        return null;
    }

    @Override
    public void deleteAds(Integer id) {

    }

    @Override
    public Ads updateAds(Integer id, CreateAds createAds, Authentication authentication) {
        return null;
    }

    @Override
    public FullAds getFullAds(Integer id) {
        return null;
    }

    @Override
    public ResponseWrapperAds getAllAds() {
        return null;
    }

    @Override
    public ResponseWrapperAds getAdsMe(Authentication authentication) {
        return null;
    }
}
