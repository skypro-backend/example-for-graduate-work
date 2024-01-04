package ru.skypro.kakavito.mappers;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.dto.AdsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateAdDTO;
import ru.skypro.kakavito.dto.ExtendedAdDTO;
import ru.skypro.kakavito.model.Ad;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class AdMapper {

    private final ModelMapper modelMapper;

    @Value("${query.to.get.image}")
    private String imageQuery;

    public AdsDTO convertToAdsDTO(List<Ad> ad) {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setCount(ad.size());
        adsDTO.setResults(ad.stream().map(this::convertToAdDTO).collect(Collectors.toList()));
        return adsDTO;
    }

    public AdDTO convertToAdDTO(Ad ad) {
        AdDTO adDTO = modelMapper.map(ad, AdDTO.class);
        adDTO.setId(ad.getId());
        adDTO.setAuthor(Math.toIntExact(ad.getUser() != null ? ad.getUser().getId() : null));
        adDTO.setImage(imageQuery + ad.getImage().getId());
        return adDTO;
    }

    public ExtendedAdDTO convertToExtendedAd(Ad ad) {
        ExtendedAdDTO extendedAdDTO = modelMapper.map(ad, ExtendedAdDTO.class);
        extendedAdDTO.setId(ad.getId());
        if (ad.getUser() != null) {
            extendedAdDTO.setAuthorFirstName(ad.getUser().getFirstName());
            extendedAdDTO.setAuthorLastName(ad.getUser().getLastName());
            extendedAdDTO.setEmail(ad.getUser().getEmail());
            extendedAdDTO.setPhone(ad.getUser().getPhone());
            extendedAdDTO.setImage(imageQuery + ad.getImage().getId());
            extendedAdDTO.setDescription(ad.getDescription());
            extendedAdDTO.setTitle(ad.getTitle());
            extendedAdDTO.setPrice(ad.getPrice());
        }
        return extendedAdDTO;
    }

    public Ad convertCreatDTOToAd(CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return modelMapper.map(createOrUpdateAdDTO, Ad.class);
    }
}

