package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.projections.ExtendedAd;

public class AdMapper {
    public static AdDTO toAdDto(AdModel adModel) {
        AdDTO adDTO = new AdDTO();
        adDTO.setPk(adModel.getPk());
        adDTO.setTitle(adModel.getTitle());
        adDTO.setPrice(adModel.getPrice());
        adDTO.setAuthor(adModel.getUserModel().getId());
        adDTO.setImage("/image/" + adModel.getImage().getId());
        return adDTO;
    }

    public static ExtendedAd getExtendedAd(AdModel ad) {
        ExtendedAd extendedAd = new ExtendedAd();
        extendedAd.setPk(ad.getPk());
        extendedAd.setAuthorFirstName(ad.getUserModel().getFirstName());
        extendedAd.setAuthorLastName(ad.getUserModel().getLastName());
        extendedAd.setEmail(ad.getUserModel().getUserName());
        extendedAd.setPhone(ad.getUserModel().getPhone());
        extendedAd.setTitle(ad.getTitle());
        extendedAd.setDescription(ad.getDescription());
        extendedAd.setImage("/image/" + ad.getImage().getId());
        extendedAd.setPrice(ad.getPrice());
        return extendedAd;
    }

//    public static AdModel toAdModel(AdDTO adDTO) {
//        AdModel adModel = new AdModel();
//        adModel.setTitle(adDTO.getTitle());
//        adModel.setPk(adDTO.getPk());
//        adModel.setImage(adDTO.getImage());
//        adModel.setPrice(adDTO.getPrice());
//        return adModel;
//    }
}