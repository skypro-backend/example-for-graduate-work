package ru.skypro.homework.service;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
@Service
public interface AdService {
//    AdsDto getAll ();
CreateOrUpdateAdDto addAds(CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication);

    AdsDto getAllAds(Authentication authentication);
}
