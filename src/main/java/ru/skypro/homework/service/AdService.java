package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;

import java.util.List;

public interface AdService {
    AdsDTO getAllAds();
    AdDTO addAd(AdDTO ad, MultipartFile image);
}
