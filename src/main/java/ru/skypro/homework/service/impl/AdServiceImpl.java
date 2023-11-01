package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public AdDTO createAd(CreateOrUpdateAd createdAd, MultipartFile img) {

        Ad ad = adRepository.save(createdAd.toAd(new Ad()));

        return AdDTO.fromAd(ad);
    }

    @Override
    public CreateOrUpdateAd updateAd(CreateOrUpdateAd createOrUpdateAd, int ad_id) {

        Optional<Ad> adOptional = adRepository.findById(ad_id); //.stream().findFirst().get();

        if (!adOptional.isEmpty()) {
            return CreateOrUpdateAd.fromAd(adRepository.save(createOrUpdateAd.toAd(adOptional.stream().findFirst().get())));
        } else {
            return null;
        }
    }

    @Override
    public AdDTO getAd(int ad_id) {
        return AdDTO.fromAd(adRepository.findById(ad_id).orElse(new Ad()));
    }

    public List<AdDTO> getAds() {
        return adRepository.findAll().stream().map(ad -> AdDTO.fromAd(ad)).toList();
    }

    @Override
    public void deleteAd(int ad_id) {
        adRepository.deleteById(ad_id);
    }

}
