package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.service.AdService;

import static ru.skypro.homework.service.ReturnableStubs.*;

@Service
public class AdServiceImpl implements AdService {

    @Override
    public AdsDTO getAllAds() {
        return getAdsDto();
    }

    @Override
    public AdDTO addAd() {
        return getListAdDTO().get(0);
    }

    @Override
    public ExtendedAd getAdFullInfo(int id) {
        return getExtendedAdd();
    }

    @Override
    public AdsDTO updateAd() {
        return null;
    }

    @Override
    public void deleteAd() {
    }

    @Override
    public AdsDTO getUserAdds() {
        return getAdsDto();
    }

    @Override
    public String updateImage() {
        return "new path";
    }
}
