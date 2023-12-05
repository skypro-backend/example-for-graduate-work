package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AdMapper {

    private Logger LoggerFactory;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(AdMapper.class));

    public AdDTO mapToDTO(Ad ad){
        return new AdDTO(
                ad.getAuthor().getId(),
                "/image/" + ad.getImage().getId(),
                ad.getPk(),
                ad.getPrice(),
                ad.getTitle());
    }

    public Ad mapToEntity(AdDTO adDTO) {
        return new Ad(
                userRepository.findById(adDTO.getAuthor()).get(),
                adRepository.findByPk(adDTO.getPk()).getImage(),
                adDTO.getPk(),
                adDTO.getPrice(),
                adDTO.getTitle(),
                adRepository.findByPk(adDTO.getPk()).getDescription(),
                adRepository.findByPk(adDTO.getPk()).getComments()
        );
    }

    public ExtendedAd mapToExtended(ru.skypro.homework.model.Ad ad) {
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
