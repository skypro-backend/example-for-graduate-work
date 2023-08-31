package ru.skypro.homework.dto.ads;

import org.mapstruct.*;
import ru.skypro.homework.entity.ads.Ad;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {
    Ad toEntity(AdDto adDto);

    List<Ad> toEntityList(List<AdDto> adDtos);

    default List<Ad> toEntity(AdsDto adsDto) {
        if (adsDto == null) {
            return null;
        }
        return toEntityList(adsDto.getResults());
    }

    Ad toEntity(CreateOrUpdateAdDto createOrUpdateAdDto);

    Ad toEntity(ExtendedAdDto extendedAdDto);

    AdDto toAdDto(Ad ad);

    List<AdDto> toListDto(List<Ad> adList);

    default AdsDto toAdsDto(List<Ad> ads) {
        if (ads == null) {
            return null;
        }
        AdsDto adsDto = new AdsDto();
        adsDto.setResults(toListDto(ads));
        adsDto.setCount(ads.size());
        return adsDto;
    }

    CreateOrUpdateAdDto toCreateOrUpdateDto(Ad ad);

    ExtendedAdDto toExtendedAdDto(Ad ad);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ad partialUpdate(AdDto adDto, @MappingTarget Ad ad);
}