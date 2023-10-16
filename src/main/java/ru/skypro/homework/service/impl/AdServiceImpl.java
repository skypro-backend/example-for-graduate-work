package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.AdsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    private final AdMapper adMapper;

    private final UserRepository userRepository;

    public AdsDto getAllAds() {
        return adMapper.toAdsDto(adRepository.findAll());
    }

    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User currentUser = userRepository.findByEmail(username).orElseThrow();
        Ad ad = adMapper.toAd(createOrUpdateAdDto);
        ad.setImage(null);
        ad.setAuthor(currentUser);
        return adMapper.toAdDto(adRepository.save(ad));
    }

    public ExtendedAdDto getAdInformation(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        return adMapper.toExtendedAdDto(ad);
    }

    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    public AdDto updateAdInformation(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad ad = adRepository.findById(id).orElseThrow();
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(createOrUpdateAdDto.getPrice());
        ad.setDescription(createOrUpdateAdDto.getDescription());
        return adMapper.toAdDto(adRepository.save(ad));
    }

    public AdsDto getAuthorizedUserAds() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User currentUser = userRepository.findByEmail(username).orElseThrow();
        return adMapper.toAdsDto(adRepository.findByAuthor(currentUser));
    }
}
