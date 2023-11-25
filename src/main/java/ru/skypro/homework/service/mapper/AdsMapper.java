package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.model.Ads;

@Mapper (componentModel = "spring")
public interface AdsMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    AdsDTO adsToAdsDto(Ads ads);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    //@Mapping(source = "imageModel", target = "image")
    @Mapping(source = "author.phone", target = "phone")
    AdsInfoDTO adsToAdsInfoDto(Ads ads);

    Ads createAdsDtoToModel(CreateAdsDTO createAdsDTO);

}
