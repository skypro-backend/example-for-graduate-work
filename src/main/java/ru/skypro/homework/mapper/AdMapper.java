package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.Ad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AdMapper {

    public AdDTO mapAdToAdDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setPk(ad.getId());
        adDTO.setAuthor(ad.getAuthor().getId());
        adDTO.setPrice(String.valueOf(ad.getPrice()));
        adDTO.setImage("/ads/" + ad.getImage().getId() + "/image");
        adDTO.setTitle(ad.getTitle());
        return adDTO;
    }

    public Ad mapAdDTOtoAd(AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setId(adDTO.getPk());
        ad.getAuthor().setId(adDTO.getAuthor());
        ad.setPrice(Integer.valueOf(adDTO.getPrice()));
        ad.getImage().setId(Integer.valueOf(adDTO.getImage()));
        ad.setTitle(adDTO.getTitle());
        return ad;
    }

    public ExtendedAdDTO mapAdToExtendedAdDTO(Ad ad) {
        ExtendedAdDTO extendedAdDTO = new ExtendedAdDTO();
        extendedAdDTO.setPk(ad.getId());
        extendedAdDTO.setAuthorFirstName(ad.getAuthor().getFirstName());
        extendedAdDTO.setAuthorLastName(ad.getAuthor().getLastName());
        extendedAdDTO.setEmail(ad.getAuthor().getEmail());
        extendedAdDTO.setPhone(ad.getAuthor().getPhone());
        extendedAdDTO.setTitle(ad.getTitle());
        extendedAdDTO.setDescription(ad.getDescription());
        extendedAdDTO.setImage("/ads/" + ad.getImage().getId() + "/image");
        extendedAdDTO.setPrice(ad.getPrice());
        return extendedAdDTO;
    }

    public Ad mapCreateOrUpdateAdDTOtoAd(CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = new Ad();
        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        return ad;
    }

    public Collection<AdDTO> mapAdListToAdDTOList(Collection<Ad>adCollection) {
        List<AdDTO> dtoList = new ArrayList<>();
        for (Ad ad: adCollection) {
            dtoList.add(mapAdToAdDTO(ad));
        }
        return dtoList;

    }
}
