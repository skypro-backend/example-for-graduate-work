package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.mapper.AdvertMapper;
import ru.skypro.homework.model.Advert;
import ru.skypro.homework.repository.AdvertRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;

    @Autowired
    public AdvertService(AdvertRepository advertRepository, AdvertMapper advertMapper) {
        this.advertRepository = advertRepository;
        this.advertMapper = advertMapper;
    }

    public List<AdDto> getAllAdverts() {
        List<Advert> adverts = advertRepository.findAll();
        return advertMapper.advertsToAdvertDtos(adverts);
    }

    public Optional<AdDto> getAdvertById(int advertId) {
        Optional<Advert> advert = advertRepository.findById(advertId);
        return advert.map(advertMapper::advertToAdvertDto);
    }

    public AdDto createAdvert(AdDto advertDto) {
        Advert advert = advertMapper.advertDtoToAdvert(advertDto);
        Advert savedAdvert = advertRepository.save(advert);
        return advertMapper.advertToAdvertDto(savedAdvert);
    }

    public Optional<AdDto> updateAdvert(int advertId, AdDto advertDto) {
        Optional<Advert> existingAdvert = advertRepository.findById(advertId);
        if (existingAdvert.isPresent()) {
            Advert updatedAdvert = advertMapper.updateAdFromDto(advertDto, existingAdvert.get());
            Advert savedAdvert = advertRepository.save(updatedAdvert);
            return Optional.of(advertMapper.advertToAdvertDto(savedAdvert));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteAdvert(int advertId) {
        if (advertRepository.existsById(advertId)) {
            advertRepository.deleteById(advertId);
            return true;
        } else {
            return false;
        }
    }
}
