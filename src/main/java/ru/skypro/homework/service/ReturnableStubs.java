package ru.skypro.homework.service;


import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.projections.ExtendedAd;

import java.util.List;

// позже подлежит удалению.
public class ReturnableStubs {


    // возвращает список объявлений
    public static List<AdDTO> getListAdDTO() {
        return List.of(new AdDTO(1, "path1", 1, 100, "title1"),
                new AdDTO(2, "path2", 2, 200, "title2"));
    }


    public static AdsDTO getAdsDto() {
        return new AdsDTO(getListAdDTO().size(), getListAdDTO());
    }

    public static ExtendedAd getExtendedAdd() {
        AdDTO adDTO = getListAdDTO().get(0);
        return new ExtendedAd(adDTO.getPk(),
                "name1",
                "surname1",
                "description1",
                "email1", adDTO.getImage(),
                "phone1", adDTO.getPrice(),
                adDTO.getTitle());
    }


}


