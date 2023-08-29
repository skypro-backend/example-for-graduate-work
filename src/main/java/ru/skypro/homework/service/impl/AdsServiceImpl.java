package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final UserServiceImpl userService;
    private final AdsRepository adsRepository;
    private final ImageServiceImpl imageService;

    @Override
    public AdsDto create(MultipartFile imageFiles, CreateOrUpdateAd createOrUpdateAd, Authentication authentication) throws IOException {
        return null;
    }

    @Override
    public AdsDto update(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        return null;
    }

    @Override
    public void updateAdsImage(Integer id, MultipartFile imageFile) throws IOException {

    }

    @Override
    public List<AdsDto> get(Authentication authentication) {
        return null;
    }

    @Override
    public ExtendedAd getAdsById(Integer id) {
        return null;
    }

    @Override
    public List<AdsDto> getAllAds() {
        log.debug("Getting all ads");
        return adsRepository.findAll()
                .stream()
                .map(AdsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Integer id) {

    }
}
