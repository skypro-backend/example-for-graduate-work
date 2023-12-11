package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AdMapper {

    private Logger LoggerFactory;

    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(AdMapper.class));

    /**
     * Mapping entity to DTO
     * @param ad
     * @return AdDTO
     */
    public AdDTO mapToDTO(Ad ad){
        return new AdDTO(
                ad.getAuthor().getId(),
                "/image/" + ad.getImage().getId(),
                ad.getPk(),
                ad.getPrice(),
                ad.getTitle());
    }

    /**
     * Mapping entity to extended version of DTO
     * @param ad
     * @return
     */
    public ExtendedAd mapToExtended(Ad ad) {
        return new ExtendedAd(
                ad.getPk(),
                ad.getAuthor().getFirstName(),
                ad.getAuthor().getLastName(),
                ad.getDescription(),
                ad.getAuthor().getUsername(),
                "/image/" + ad.getImage().getId(),
                ad.getAuthor().getPhone(),
                ad.getPrice(),
                ad.getTitle()
        );
    }

    /**
     * Mapping list of ads to "Ads" DTO
     * @param ads
     * @return
     */
    public Ads mapToListOfDTO(List<Ad> ads) {
        List<AdDTO> results = new ArrayList<>();
        for (int i = 0; i < ads.size(); i++) {
            results.add(mapToDTO(ads.get(i)));
        }
        return new Ads(
            ads.size(),
            results
        );
    }

    /**
     * Creating entity from DTO
     * @param createOrUpdateAd
     * @param user
     * @return
     */
    public Ad createFromCreateOrUpdateAd(CreateOrUpdateAd createOrUpdateAd, User user) {
        logger.info("ДТО регистрации - " + createOrUpdateAd);
        Ad ad = new Ad();
        ad.setAuthor(user);
        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setDescription(createOrUpdateAd.getDescription());
        return ad;
    }

}
