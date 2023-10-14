package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.AdsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdService {

    private final AdRepository adRepository;

    private final AdMapper adMapper;

    public AdsDto getAllAds() {
        List<AdDto> adDtoList = adRepository.findAll().stream().map(adMapper::toAdDto).collect(Collectors.toList());
        return new AdsDto(adRepository.findAll().size(), adDtoList);
        //  мб можно добавить маппер toAdsDto, только разобраться как записать туда лист
    }

    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        Integer id = adRepository.save(adMapper.toAd(createOrUpdateAdDto)).getId();
        Ad savedAd = adRepository.findById(id).orElseThrow();
        return adMapper.toAdDto(savedAd);
    }
}
