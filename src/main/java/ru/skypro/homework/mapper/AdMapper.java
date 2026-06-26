package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    AdDto toAdDto(Ad ad);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    ExtendedAd toExtendedAd(Ad ad);

    Ad toEntity(CreateOrUpdateAd dto);

    void updateAdFromDto(CreateOrUpdateAd dto, @MappingTarget Ad ad);

    // Добавил сигнатуру для маппинга коллекции
    List<AdDto> toAdDtoList(List<Ad> ads);

    default Ads toAds(List<Ad> ads) {
        Ads adsDto = new Ads();
        adsDto.setCount(ads.size());
        adsDto.setResults(toAdDtoList(ads));
        return adsDto;
    }
}