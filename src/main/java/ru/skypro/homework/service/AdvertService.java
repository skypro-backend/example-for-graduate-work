package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.exception.AdvertNotFoundException;
import ru.skypro.homework.mapper.AdvertMapper;
import ru.skypro.homework.model.Advert;
import ru.skypro.homework.repository.AdvertRepository;

import java.util.List;

@Service
@Slf4j
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;

    public AdvertService(AdvertRepository advertRepository, AdvertMapper advertMapper) {
        this.advertRepository = advertRepository;
        this.advertMapper = advertMapper;
    }

    public AdsDto create(CreateAdsDto properties) {
        log.info("Creat advert with properties: " + properties);
        Advert advert = advertMapper.createAdsDtoToAdvert(properties);
        return advertMapper.advertToAdsDto(advertRepository.save(advert));
    }

    public void delete(int id) {
        log.info("Delete advert with id: " + id);
        Advert advert = advertRepository.findById(id)
                .orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
        advertRepository.delete(advert);
    }

    public AdsDto update(int id, CreateAdsDto properties) {
        log.info("Update advert with id: " + id);
        Advert advert = advertRepository.findById(id)
                .orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
        advertMapper.updateAdvert(properties, advert);
        advertRepository.save(advert);
        return advertMapper.advertToAdsDto(advert);
    }

    public ResponseWrapperAdsDto findAll() {
        log.info("Find all adverts");
        List<Advert> adverts = advertRepository.findAll();
        return advertMapper.listToRespWrapperAdsDto(adverts);
    }

    public FullAdsDto findById(int id) {
        log.info("Find advert by id: " + id);
        Advert advert = advertRepository.findById(id)
                .orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
        return advertMapper.advertToFullAdsDto(advert);
    }

    public ResponseWrapperAdsDto findAllByAuthUser(String name) {
        log.info("Find adverts by user name: " + name);
        //to be done
        return null;
    }
}
