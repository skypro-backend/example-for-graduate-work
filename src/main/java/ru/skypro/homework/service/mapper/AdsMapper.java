package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.model.Ads;

@Mapper (componentModel = "spring")
public interface AdsMapper {
    AdsDTO adsToAdsDto(Ads ads);

    AdsInfoDTO adsToAdsInfoDto(Ads ads);

    Ads createAdsDtoToModel(CreateAdsDTO createAdsDTO);
}
