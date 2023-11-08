package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;

//@Service
//@AllArgsConstructor
//public class AdServiceImpl implements AdService {

//
//    private final AdRepository adRepository;
//
//    @Override
//    public AdsDto getAll() {
//        List<Ad> getAllAd = adRepository.findAll();
//        AdsDto newGetAll = new AdsDto();
//        newGetAll.setCount(getAllAd.size());
//        newGetAll.setResults(AdMapper.INSTANCE.gto(getAllAd));
//        return newGetAll;
//    }

//    @Override
//    public AdDto getAll() {
//        AdDto newAdDto = new AdDto();
//        newAdDto.setAuthor(getAll().getAuthor());
//
//        return null;
//    }

//}
