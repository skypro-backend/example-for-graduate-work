package ru.skypro.flea.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.flea.dto.AdDto;
import ru.skypro.flea.dto.AdsDto;
import ru.skypro.flea.dto.CreateOrUpdateAdDto;
import ru.skypro.flea.dto.ExtendedAdDto;
import ru.skypro.flea.model.Ad;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AdMapper {

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "pk", source = "id")
    AdDto toAdDto(Ad entity);

    List<AdDto> toAdDtoList(Collection<Ad> ads);

    default AdsDto toAdsDto(Collection<Ad> ads) {
        AdsDto dto = new AdsDto();
        dto.setResults(toAdDtoList(ads));
        dto.setCount(ads.size());

        return dto;
    }

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phone", source = "user.phone")
    ExtendedAdDto toExtendedAdDto(Ad entity);

    Ad createAdFromDto(String image, CreateOrUpdateAdDto dto);

    void updateAdFromDto(@MappingTarget Ad ad, CreateOrUpdateAdDto dto);

}
