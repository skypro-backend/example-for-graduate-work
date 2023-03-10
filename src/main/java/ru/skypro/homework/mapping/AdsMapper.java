package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.entity.AdsEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdsMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "author", source = "author")
    @Mapping(target = "imageId", source = "imageId")
    AdsEntity toModel(Ads dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "author", target = "author")
    @Mapping(target = "imageId", source = "imageId")
    Ads toDto(AdsEntity entity);

    List<Ads> toAdsDtoList(List<AdsEntity> entityList);

    List<AdsEntity> toAdsEntityList(List<Ads> dtoList);
}
