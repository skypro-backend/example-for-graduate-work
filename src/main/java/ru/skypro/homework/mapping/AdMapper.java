package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.model.Ad;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
    AdDto adToDto (Ad ad);

    Ad adDtoToad(AdDto dto);

    Ad CrOUpdToAd(CreateOrUpdateAdDto createOrUpdateAdDto);

}
