package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.AdsEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface CreateAdsMapper {
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "title", source = "title")
    AdsEntity toModel(CreateAds dto);
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "title", source = "title")
    CreateAds toDto(AdsEntity entity);
}
