package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.model.Ads;

import java.util.List;

@Component
public class AdsMapper {

    public AdsDTO adsToAdsDto(Ads ads) {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setAuthor(ads.getAuthor().getId());
        if (ads.getImage() != null) {
            adsDTO.setImage("/ads/image/" + ads.getImage().getId());
        }
        adsDTO.setPk(ads.getPk());
        adsDTO.setPrice(ads.getPrice());
        adsDTO.setTitle(ads.getTitle());

        return adsDTO;
    }


    public AdsInfoDTO adsToAdsInfoDto(Ads ads) {
        AdsInfoDTO adsInfoDTO = new AdsInfoDTO();
        adsInfoDTO.setPk(ads.getPk());
        adsInfoDTO.setAuthorFirstName(ads.getAuthor().getFirstName());
        adsInfoDTO.setAuthorLastName(ads.getAuthor().getLastName());
        adsInfoDTO.setDescription(ads.getDescription());
        adsInfoDTO.setEmail(ads.getAuthor().getEmail());
        if (ads.getImage() != null) {
            adsInfoDTO.setImage("/ads/image/" + ads.getImage().getId());
        }
        adsInfoDTO.setPhone(ads.getAuthor().getPhone());
        adsInfoDTO.setPrice(ads.getPrice());
        adsInfoDTO.setTitle(ads.getTitle());

        return adsInfoDTO;
    }


    public Ads createAdsDtoToModel(CreateAdsDTO createAdsDTO) {
        Ads ads = new Ads();
        ads.setTitle(createAdsDTO.getTitle());
        ads.setDescription(createAdsDTO.getDescription());
        ads.setPrice(createAdsDTO.getPrice());

        return ads;
    }

}
