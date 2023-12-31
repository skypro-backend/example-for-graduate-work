package ru.skypro.kakavito.mappers;

import lombok.Data;
import org.modelmapper.ModelMapper;
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

    public AdsDTO convertToAdsDTO(List<Ad> ad) {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setCount(ad.size());
        adsDTO.setResults(ad.stream().map(this::convertToAdDTO).collect(Collectors.toList()));
        return adsDTO;
    }

    public AdDTO convertToAdDTO(Ad ad) {
        return modelMapper.map(ad, AdDTO.class);
    }

    public ExtendedAdDTO convertToExtendedAd(Ad ad) {
        return modelMapper.map(ad, ExtendedAdDTO.class);
    }

    public Ad convertCreatDTOToAd(CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return modelMapper.map(createOrUpdateAdDTO, Ad.class);
    }
}
