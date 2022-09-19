package ru.skypro.homework.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.entity.Ads;

@Mapper
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "image.pk")
    AdsDto toAdsDto(Ads ads);

    @Mapping(target = "image", source = "image.pk")
    CreateAdsDto toCreateAdsDto(Ads ads);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "image.pk")
    FullAdsDto toDullAdsDto(Ads ads);

    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "image.pk", source = "image")
    Ads toAds(AdsDto adsDto);

    @Mapping(target = "image.pk", source = "image")
    Ads fromCreateAdsToAds(CreateAdsDto createAdsDto);

    @Mapping(target = "image.pk", source = "image")
    @Mapping(target = "author.firstName", source = "authorFirstName")
    @Mapping(target = "author.lastName", source = "authorLastName")
    @Mapping(target = "author.email", source = "authorLastName")
    @Mapping(target = "author.phone", source = "phone")
    Ads fromFullAdsToAds(FullAdsDto fullAdsDto);

}
