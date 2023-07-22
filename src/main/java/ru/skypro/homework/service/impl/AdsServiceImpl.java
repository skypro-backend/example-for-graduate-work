package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
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
    public void createAds(CreateAds createAds) {
   ///    Ad addAd =  adsRepository.saveAndFlush(createAds);
  ///      return null;
    }

    @Override
    public Ad getAdById(int id) {
        try {
            return adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
        } catch (AdNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAd(int id) {
        adsRepository.deleteById(id);

    }

    @Override
    public ResponseWrapperAds getAllAds() {
        AdsDTO[] ads = (AdsDTO[]) adsRepository.findAll().stream()
                .map(AdsDTO::fromAd).toArray();


        int count = ads.length;

        return new ResponseWrapperAds(count, ads);

    }

    @Override
    public AdsDTO updateAd(int id, CreateAds createAds) {
        Ad ad;
        try {
            ad = adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
        } catch (AdNotFoundException e) {
            throw new RuntimeException(e);
        }

        ad.setText(createAds.getDescription());
        ad.setPrice(createAds.getPrice());
        ad.setTitle(createAds.getTitle());

        adsRepository.saveAndFlush(ad);
        return AdsDTO.fromAd(ad);
    }


    @Override
    public FullAds getFullAdById(int adId) {
        Ad ad;
        try {
            ad = adsRepository.findById(adId).orElseThrow(AdNotFoundException::new);

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
