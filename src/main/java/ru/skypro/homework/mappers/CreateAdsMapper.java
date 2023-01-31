package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.model.CreateAds;

@Mapper
public interface CreateAdsMapper {
    CreateAdsDto toCreateAdsDTO (CreateAds createAds);

    CreateAds toCreateAds (CreateAdsDto createAdsDto);
}
