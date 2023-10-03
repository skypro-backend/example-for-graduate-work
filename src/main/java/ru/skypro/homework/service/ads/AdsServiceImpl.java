package ru.skypro.homework.service.ads;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.exception.AdsNotFound;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.projection.ExtendedAd;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class AdsServiceImpl implements AdsService{

    private final AdRepository adRepository;
    @Override
    public List<AdDTO> getAllAds() {
        return adRepository.findAllAds().stream()
                .map(AdMapper::fromAd)
                .collect(Collectors.toList());
    }

    @Override
    public AdDTO createAd(CreateOrUpdateAd properties, MultipartFile image) {
        Ad ad = new Ad()
                .setTitle(properties.getTitle())
                .setPrice(properties.getPrice())
                .setDescription(properties.getDescription());
        return AdMapper.fromAd(adRepository.save(ad));
    }

    @Override
    public ExtendedAd getAdFullInfo(Integer id) {
        return adRepository.findAllAdFullInfo(id).orElseThrow(AdsNotFound::new);
    }

    @Override
    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAd properties) {
        Ad adResult = new Ad()
                .setTitle(properties.getTitle())
                .setDescription(properties.getDescription())
                .setPrice(properties.getPrice());
        adResult.setTitle(adRepository
                        .findById(id)
                        .orElseThrow()
                        .getTitle())
                .setDescription(adRepository
                        .findById(id)
                        .orElseThrow()
                        .getDescription())
                .setPrice(adRepository
                        .findById(id)
                        .orElseThrow()
                        .getPrice());

        return AdMapper.fromAd(adRepository.save(adResult));
    }

    @Override
    public List<AdDTO> getAllAdsByUser(String user) {
        return adRepository.getAllAdsByUser(user).stream()
                .map(AdMapper::fromAd)
                .collect(Collectors.toList());
    }

    @Override
    public void updateImage(Integer id, MultipartFile image) {

    }
}
