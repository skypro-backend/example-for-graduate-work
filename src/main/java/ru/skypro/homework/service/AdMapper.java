package ru.skypro.homework.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;

import java.util.Collection;

@Mapper
@Component
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(source = "author_id", target = "author")
    })
    Ad adToDto(AdEntity ad);
    @Mappings({
            @Mapping(target = "count", expression = "java(ads.size())"),
            @Mapping(target = "results", source = "ads")
    })
    Ads adToDtoList(Collection<AdEntity> ads);

    AdEntity DtoToAd(CreateOrUpdateAd createOrUpdateAd);

    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(target = "authorFirstName", expression = "java(ad.getAuthor().getFirstName())"),
            @Mapping(target = "authorLastName", expression = "java(ad.getAuthor().getLastName())"),
            @Mapping(target = "email", expression = "java(ad.getAuthor().getEmail())"),
            @Mapping(target = "phone", expression = "java(ad.getAuthor().getPhone())")
    })
    ExtendedAd adToExtDto(AdEntity ad);
}
