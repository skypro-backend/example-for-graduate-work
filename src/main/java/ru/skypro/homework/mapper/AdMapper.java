package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.model.Ad;

@Service
public class AdMapper {

    //из model в dto
    public AdDTO mapToAdDto(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setImage(ad.getImage());
        adDTO.setPk(ad.getPk());
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());
        //    adDTO.setDescription(ad.getDescription());
        return adDTO;
    }

    public CreateOrUpdateAd mapToCreateOrUpdateAdDTO(Ad ad) {
        CreateOrUpdateAd adDTO = new CreateOrUpdateAd();
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());
        adDTO.setDescription(ad.getDescription());
        return adDTO;
    }

    public ExtendedAdDTO mapToExtendedAdDTO(Ad ad) {
        ExtendedAdDTO extendedAdDTO = new ExtendedAdDTO();
        extendedAdDTO.setPk(ad.getPk());
        extendedAdDTO.setFirstName(ad.getAuthor().getFirstName());
        extendedAdDTO.setLastName(ad.getAuthor().getLastName());
        extendedAdDTO.setDescription(ad.getDescription());
        extendedAdDTO.setEmail(ad.getAuthor().getUsername());
        extendedAdDTO.setImage(ad.getImage());
        extendedAdDTO.setPhone(ad.getAuthor().getPhone());
        extendedAdDTO.setPrice(ad.getPrice());
        extendedAdDTO.setTitle(ad.getTitle());
        return extendedAdDTO;
    }

    //из dto в model
    public Ad mapToAd(AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setImage(adDTO.getImage());
        ad.setPk(adDTO.getPk());
        ad.setPrice(adDTO.getPrice());
        ad.setTitle(adDTO.getTitle());
        //     ad.setDescription(adDTO.getDescription());
        return ad;
    }

    public Ad mapToAdFromCreateOrUpdateAd(CreateOrUpdateAd createOrUpdateAd) {
        Ad ad = new Ad();
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setDescription(createOrUpdateAd.getDescription());
        return ad;
    }

}
