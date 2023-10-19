package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;

import static ru.skypro.homework.service.ReturnableStubs.*;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    @Override
    public AdsDTO getAllAds() {
        return new AdsDTO(1, List.of(new AdDTO(1, "testPath", 321, 123, "testTitle")));
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAd createAd, String imagePath) {
        return getListAdDTO().get(0);
    }

    @Override
    public ExtendedAd getAdFullInfo(int id) {
        return new ExtendedAd(23,
                "name1",
                "surname1",
                "description1",
                "email1",
                "testPath",
                "phone1",
                777,
                "testTitle");
    }


    @Override
    public void deleteAd(int id) {

    }

    @Override
    public AdsDTO updateAd(CreateOrUpdateAd updateAd, int id) {
        return new AdsDTO(1, List.of(new AdDTO(1, "testPath", 321, 123, "testTitle")));
    }

    public AdsDTO getUserAdds() {
        return new AdsDTO(1, List.of(new AdDTO(1, "testPath", 321, 123, "testTitle")));
    }

    @Override
    public String updateImage() {
        return "new path";
    }
}
