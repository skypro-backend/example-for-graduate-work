package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.model.Ad;

@Service
public class AdMapper {

    //из model в dto
    public AdDTO mapToAdDto(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setAuthor(ad.getAuthor());
        adDTO.setImage(ad.getImage());
        adDTO.setPk(ad.getPk());
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());
        return adDTO;
    }

    public CreateOrUpdateAd mapToCreateOrUpdateAdDTO(Ad ad) {
        CreateOrUpdateAd adDTO = new CreateOrUpdateAd();
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());
        return adDTO;
    }

    //из dto в model
    public Ad mapToAd(AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setAuthor(adDTO.getAuthor());
        ad.setImage(adDTO.getImage());
        ad.setPk(adDTO.getPk());
        ad.setPrice(adDTO.getPrice());
        ad.setTitle(adDTO.getTitle());
        return ad;
    }

    public Ad mapToCreateOrUpdateAd(CreateOrUpdateAd CreateOrUpdateAd) {
        Ad ad = new Ad();
        ad.setPrice(CreateOrUpdateAd.getPrice());
        ad.setTitle(CreateOrUpdateAd.getTitle());
        return ad;
    }

}
