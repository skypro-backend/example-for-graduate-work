package ru.skypro.homework.mappers;

import org.mapstruct.*;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.users.User;

import java.util.List;

@Mapper(componentModel = "spring", uses = CustomMapper.class)
public interface AdMapper {

    Ad toEntity(AdDto adDto);

    Ad toEntity(CreateOrUpdateAdDto createOrUpdateAdDto);

    Ad toEntity(ExtendedAdDto extendedAdDto);

    List<Ad> toEntityList(List<AdDto> adDtos);

    default List<Ad> toEntityList(AdsDto adsDto) {
        return toEntityList(adsDto.getResults());
    }

    AdDto toAdDto(Ad ad);

    List<AdDto> toListDto(List<Ad> adList);

    CreateOrUpdateAdDto toCreateOrUpdateDto(Ad ad);

    default Integer map(User value) {
        return value != null ? value.getId() : null;
    }

    ExtendedAdDto toExtendedAdDto(Ad ad);

}