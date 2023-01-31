package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ads;

@Mapper
public interface AdsMapper {
    AdsDto toAdsDTO (Ads Ads);
    Ads toAds(AdsDto dto);
}
