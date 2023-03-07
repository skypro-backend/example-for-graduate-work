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
    @Mapping(target = "user", ignore = true)
  //  @Mapping(target = "imageEntity", )
    AdsEntity toModel(Ads dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "author")
    // @Mapping(target = "image", )
    Ads toDto(AdsEntity entity);

    List<Ads> toAdsDtoList(List<AdsEntity> entityList);

    List<AdsEntity> toAdsEntityList(List<Ads> dtoList);
}
