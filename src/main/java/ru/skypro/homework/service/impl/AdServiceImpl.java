package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final UserService userService;
    private final AdMapper adMapper;

    @Override
    public void removeAd(Integer id) {
        adRepository.removeAdById(id);
    }

    @Override
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        return new AdDto(userService.getAuthorizedUser().id(), image.toString(), 0, createOrUpdateAdDto.price(), createOrUpdateAdDto.title());
    }

    @Override
    public ExtendedAdDto getAdById(Integer id) {
        return adMapper.toDto(adRepository.getAdById(id));
    }

    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad ad = adRepository.getAdById(id);
        ad.setPrice(createOrUpdateAdDto.price());
        ad.setTitle(createOrUpdateAdDto.title());

        return adMapper.toAdDto(ad);
    }


}
