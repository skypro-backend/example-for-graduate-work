package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repositories.AdRepository;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;
    private final AdRepository adRepository;


    public AdServiceImpl(AdMapper adMapper, AdRepository adRepository) {
        this.adMapper = adMapper;
        this.adRepository = adRepository;
    }

    //Получение информации об объявлении
    @Override
    public AdEntity getAdsById(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Объявление не найдено"));
    }

    //Добавление объявления
    @Override
    public AdDto addAd(CreateOrUpdateAd properties, MultipartFile image) {
        AdEntity adEntity = new AdEntity();

        adEntity.setDescription(properties.getDescription());
        adEntity.setPrice(properties.getPrice());
        adEntity.setTitle(properties.getTitle());

        //нужна реализация класса UserService

        adRepository.save(adEntity);
        return adMapper.mapToAdDTO(adEntity);
    }

    //Получение всех объявлений
    @Override
    public AdsDto getAllAds() {
        return (AdsDto) adRepository.findAll();
    }

    //удаление объявления
    @Override
    public void deleteAds(int id) {
        AdEntity removedAd = getAdsById(id);
        adRepository.delete(removedAd);
    }

    //обновление информации об объявлении
    @Override
    public AdDto updateAds(Integer id, CreateOrUpdateAd dto) {
        AdEntity adEntity = adRepository.findById(id).get();

        adEntity.setTitle(dto.getTitle());
        adEntity.setDescription(dto.getDescription());
        adEntity.setPrice(dto.getPrice());

        adRepository.save(adEntity);
        return adMapper.mapToAdDTO(adEntity);
    }

    //получение объявлений авторизованного пользователя
    @Override
    public AdsDto getAdsMe(String userName) {

        //нужен метод с класса UserService - "Получение информации об авторизованном пользователе"

        return null;
    }

    //обновление картинки объявления
    @Override
    public void updateImage(Integer id, MultipartFile image) {
        AdEntity adEntity = adRepository.findById(id).get();
        //пока хз как сделать
        adRepository.save(adEntity);
    }
}