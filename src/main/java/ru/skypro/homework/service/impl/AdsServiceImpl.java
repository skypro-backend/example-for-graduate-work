package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.repository.AdsRepository;
@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;

    public AdsServiceImpl(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }


    @Override
    public Ad createAds(Ad ad) {
        Ad addAd =  adsRepository.saveAndFlush(ad);
        return addAd;
    }
    @Override
    public void deleteAd(int id){
        adsRepository.deleteById(id);

    }

    @Override
    public Ad[] getAllAds() {
        return adsRepository.findAll().toArray(new Ad[0]);
    }



    @Override
    public FullAds getAdById(int adId) {
        Ad ad = null;
        try {
            ad = adsRepository.findById((int) adId).orElseThrow(AdNotFoundException::new);

        } catch (AdNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new FullAds(ad.getId(),
                ad.getUser().getFirstName(),
                ad.getUser().getLastName(),
                ad.getText(),
                ad.getUser().getPhone(),
                ad.getPrice(),
                ad.getTitle());
    }
}
