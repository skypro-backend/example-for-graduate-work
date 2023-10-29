package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Override
    public void removeAd(Integer id, Authentication authentication) {
        authentication.isAuthenticated();
        adRepository.removeAdById(id);
    }

    @Override
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        return null;
    }

    @Override
    public ExtendedAdDto getAdById(Integer id) {
        return adMapper.toDto(adRepository.getAdById(id));
    }

    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) throws AdNotFoundException {
        Ad ad = adMapper.updateAdDtoToAd(id, createOrUpdateAdDto, getUserByAdId(id));
        adRepository.save(ad);
        return adMapper.toAdDto(ad);
    }

    @Override
    public AdsDto getAuthorizedUserAds() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer id = userRepository.findByLogin(authentication.getName()).orElseThrow().getId();
        List<Ad> list = adRepository.getAdsByUserId(id);
        List<AdDto> adsDtoList = adMapper.toAdsDto(list);

        return new AdsDto(adsDtoList.size(), adsDtoList);
    }

    @Override
    public void UpdateAdImage(Integer id, final MultipartFile file) {

    }

    @Override
    public AdsDto getAllAds() {
        List<Ad> list = adRepository.findAll();
        List<AdDto> adsDtoList = adMapper.toAdsDto(list);

        return new AdsDto(adsDtoList.size(), adsDtoList);
    }

    protected User getUserByAdId(Integer adId) {
        return adRepository.findById(adId)
                .orElseThrow(() ->
                        new AdNotFoundException(adId))
                .getUser();

    }


}
