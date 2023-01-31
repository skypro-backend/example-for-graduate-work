package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.model.FullAds;

@Mapper
public interface FullAdsMapper {
    FullAdsDto toFullAdsDto(FullAds fullAds);
    FullAds toFullAds(FullAdsDto fullAdsDto);
}
