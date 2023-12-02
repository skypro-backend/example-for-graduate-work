package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdMapper {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public AdDTO mapToDTO(Ad ad){
        return new AdDTO(
                ad.getAuthor().getId(),
                ad.getImage().getLink(),
                ad.getPk(),
                ad.getPrice(),
                ad.getTitle());
    }

    public Ad mapToEntity(AdDTO adDTO) {
        return new ru.skypro.homework.model.Ad(
                userRepository.findById(adDTO.getAuthor()).get(),
                imageRepository.findByLink(adDTO.getImage()),
                adDTO.getPk(),
                adDTO.getPrice(),
                adDTO.getTitle(),
                adRepository.findByPk(adDTO.getPk()).getDescription(),
                adRepository.findByPk(adDTO.getPk()).getComments()
        );
    }

    public ExtendedAd mapToExtended(Ad ad) {
        return new ExtendedAd(
                ad.getPk(),
                ad.getAuthor().getFirstName(),
                ad.getAuthor().getLastName(),
                ad.getDescription(),
                ad.getAuthor().getUsername(),
                ad.getAuthor().getImage().getLink(),
                ad.getAuthor().getPhone(),
                ad.getPrice(),
                ad.getTitle()
        );
    }

    public User mapExtendedBackToAuthor(ExtendedAd extendedAd) {
        return adRepository.findByPk(extendedAd.getPk()).getAuthor();
    }

    public ru.skypro.homework.model.Ad mapExtendedBackToEntity(ExtendedAd extendedAd) {
        return new ru.skypro.homework.model.Ad(
                adRepository.findByPk(extendedAd.getPk()).getAuthor(),
                imageRepository.findByLink(extendedAd.getImage()),
                extendedAd.getPk(),
                extendedAd.getPrice(),
                extendedAd.getTitle(),
                extendedAd.getDescription(),
                adRepository.findByPk(extendedAd.getPk()).getComments()
        );
    }

    public Ads mapToListOfDTO(List<ru.skypro.homework.model.Ad> ads) {
        List<AdDTO> results = new ArrayList<>();
        for (int i = 0; i < ads.size(); i++) {
            results.add(mapToDTO(ads.get(i)));
        }
        return new Ads(
            ads.size(),
            results
        );
    }

    public List<ru.skypro.homework.model.Ad> mapBackToListOfEntities(Ads ads) {
        List<ru.skypro.homework.model.Ad> results = new ArrayList<>();
        for (int i = 0; i < ads.getResults().size(); i++) {
            results.add(mapToEntity(ads.getResults().get(i)));
        }
        return results;
    }

}
