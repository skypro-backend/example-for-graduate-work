package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.model.dto.CreateAdsDto;
import ru.skypro.homework.model.entity.CreateAds;

@Mapper
public interface CreateAdsMapper {
    CreateAdsDto toCreateAdsDTO (CreateAds createAds);

    CreateAds toCreateAds (CreateAdsDto createAdsDto);
}
