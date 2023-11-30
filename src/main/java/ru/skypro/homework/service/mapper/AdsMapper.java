package ru.skypro.homework.service.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.model.Ads;

/**
 * Класс, содержащий методы для преобразования класса {@link Ads} в DTO и обратно
 */

@Component
public class AdsMapper {

    /**
     * Метод преобразует {@link Ads} в {@link AdsDTO}
     * @param ads
     * @return {@link AdsDTO}
     */
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

    /**
     * Метод преобразует {@link Ads} в {@link AdsInfoDTO}
     * @param ads
     * @return {@link AdsDTO}
     */
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

    /**
     * Метод преобразует {@link CreateAdsDTO} в {@link Ads}
     * @param createAdsDTO
     * @return {@link Ads}
     */
    public Ads createAdsDtoToModel(CreateAdsDTO createAdsDTO) {
        Ads ads = new Ads();
        ads.setTitle(createAdsDTO.getTitle());
        ads.setDescription(createAdsDTO.getDescription());
        ads.setPrice(createAdsDTO.getPrice());

        return ads;
    }

}
