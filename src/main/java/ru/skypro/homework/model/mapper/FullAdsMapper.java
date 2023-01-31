package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.model.dto.FullAdsDto;
import ru.skypro.homework.model.entity.FullAds;

@Mapper
public interface FullAdsMapper {
    FullAdsDto toFullAdsDto(FullAds fullAds);
    FullAds toFullAds(FullAdsDto fullAdsDto);
}
