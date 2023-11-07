package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.model.Ads;

@Mapper (componentModel = "spring")
public interface AdsMapper {
    AdsDTO adsToDto(Ads ads);

    Ads adsDtoToModel(AdsDTO adsDTO);
}
