package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.response.AdResponseDto;
import ru.skypro.homework.dto.response.AdsResponseDto;
import ru.skypro.homework.dto.request.AdRequestDto;
import ru.skypro.homework.dto.response.ExtendedAdResponseDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    AdResponseDto adToDto(Ad ad);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.username", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    ExtendedAdResponseDto extendedAdToDto(Ad ad);

    List<AdResponseDto> adsToDtoList(List<Ad> ads);

    default AdsResponseDto toAdsResponseDto(List<Ad> ads) {
        return new AdsResponseDto(ads.size(), adsToDtoList(ads));
    }

    Ad adRequestDtoToAd(AdRequestDto dto);

    void updateAdFromDto(AdRequestDto dto, @MappingTarget Ad ad);

    default Integer map(User user) {
        return user != null ? user.getId() : null;
    }
}
