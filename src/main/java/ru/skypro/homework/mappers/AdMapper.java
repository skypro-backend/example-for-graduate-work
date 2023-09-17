package ru.skypro.homework.mappers;

import org.mapstruct.*;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.dto.ads.out.AdsDto;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.ExtendedAdDto;
import ru.skypro.homework.entity.ads.Ad;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CustomUserMapper.class)
public interface AdMapper {

    Ad toAd(CreateOrUpdateAdDto createOrUpdateAdDto);

    @Mapping(target = "author", source = "author.id")
    AdDto toAdDto(Ad ad);

    @Mappings({
            @Mapping(target = "authorFirstName", source = "author.firstName"),
            @Mapping(target = "authorLastName", source = "author.lastName"),
            @Mapping(target = "phone", source = "author.phone"),
            @Mapping(target = "email", source = "author.email")
    })
    ExtendedAdDto toExtendedAdDto(Ad ad);

    default AdsDto toAdsDto(List<Ad> adList) {
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(adList.size());
        adsDto.setResults(adList.stream().map(this::toAdDto).collect(Collectors.toList()));
        return adsDto;
    }

    void updateAds(CreateOrUpdateAdDto createOrUpdateAdDto, @MappingTarget Ad ad);

}