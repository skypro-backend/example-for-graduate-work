package ru.skypro.homework.dto.ads;

import org.mapstruct.*;
import ru.skypro.homework.entity.ads.Ad;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {
    Ad toEntity(AdDto adDto);

    List<Ad> toEntity(AdsDto adsDto);

    Ad toEntity(CreateOrUpdateAdDto createOrUpdateAdDto);

    Ad toEntity(ExtendedAdDto extendedAdDto);

    AdDto toAdDto(Ad ad);

    AdsDto toAdsDto(List<Ad> ads);

    CreateOrUpdateAdDto toCreateOrUpdateDto(Ad ad);

    ExtendedAdDto toExtendedAdDto(Ad ad);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ad partialUpdate(AdDto adDto, @MappingTarget Ad ad);
}