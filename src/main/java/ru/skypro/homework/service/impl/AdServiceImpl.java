package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.CreateOrUpdateAdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
//@RequiredArgsConstructor

public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
//    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
//    private final AdMapper adMapper;
    private final AdsMapper adsMapper;
//    private final User user;


    @Override
    public CreateOrUpdateAdDto addAds(CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication) {
        User user = userServiceImpl.findUserByUsername(authentication);
        Ad ad = CreateOrUpdateAdMapper.INSTANCE.toModel(createOrUpdateAdDto);
        ad.setUser(user);
        adRepository.save(ad);
        return CreateOrUpdateAdMapper.INSTANCE.toDto(ad, user);
    }

    @Override
    public AdsDto getAllAds(Authentication authentication) {
        User user = userServiceImpl.findUserByUsername(authentication);
//        List<Ad> adList = adRepository.findAdByUser(user);
        List<Ad> adList = adRepository.findAll();
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(adList.size());
        adsDto.setResults(adsMapper.INSTANCE.toDto(adList));
        return adsDto;
    }

}
