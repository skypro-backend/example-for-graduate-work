package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.service.AdsService;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public AdsDto getAds(int id) {
       return new AdsDto();
    }
    @Override
    public AdsDto updateAds( int id) {

    }
}
