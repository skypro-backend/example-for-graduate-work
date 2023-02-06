package ru.skypro.homework.model.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.dto.AdsDto;
import ru.skypro.homework.model.dto.CreateAdsDto;
import ru.skypro.homework.model.dto.FullAdsDto;
import ru.skypro.homework.model.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.model.entity.Ads;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);
    @Mapping(source = "id", target = "pk")
    AdsDto adsToAdsDto(Ads Ads);
    @Mapping(target = "id", source = "pk")
    Ads AdsDtoToAds(AdsDto dto);
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    FullAdsDto toFullAdsDto (Ads ads);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void partialUpdateAds(CreateAdsDto createAdsDto, @MappingTarget Ads ads);


    default ResponseWrapperAdsDto adsToResponseWrapperAdsDto(List<Ads> ads) {
        ResponseWrapperAdsDto wrapperAds = new ResponseWrapperAdsDto();
        List<AdsDto> resultAds = ads.stream()
                .map(this::adsToAdsDto).collect(Collectors.toList());
        wrapperAds.setResults(resultAds);
        wrapperAds.setCount(resultAds.size());
        return wrapperAds;
    }
}
