package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    public AdService(AdRepository adRepository, UserRepository userRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
    }

    public Ads getAllAds() {
        List<Ad> adDtos = adRepository.findAll()
                .stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());

        Ads ads = new Ads();
        ads.setCount(adDtos.size());
        ads.setResults(adDtos);
        return ads;
    }

    public Optional<ExtendedAd> getAdById(Integer id) {
        return adRepository.findById(id)
                .map(adMapper::toExtendedDto);
    }

    public Optional<Ad> addAd(CreateOrUpdateAd dto) {
        Optional<UserEntity> authorOptional = userRepository.findById(1);

        if (authorOptional.isEmpty()) {
            return Optional.empty();
        }

        AdEntity adEntity = adMapper.toEntity(dto);
        adEntity.setAuthor(authorOptional.get());
        adEntity.setImage("placeholder.jpg");

        AdEntity savedAd = adRepository.save(adEntity);
        return Optional.of(adMapper.toDto(savedAd));
    }

    public Optional<Ad> updateAd(Integer id, CreateOrUpdateAd dto) {
        Optional<AdEntity> adOptional = adRepository.findById(id);

        if (adOptional.isEmpty()) {
            return Optional.empty();
        }

        AdEntity adEntity = adOptional.get();
        adMapper.updateAdFromDto(dto, adEntity);

        AdEntity updatedAd = adRepository.save(adEntity);
        return Optional.of(adMapper.toDto(updatedAd));
    }

    public boolean deleteAd(Integer id) {
        if (!adRepository.existsById(id)) {
            return false;
        }

        adRepository.deleteById(id);
        return true;
    }
}
