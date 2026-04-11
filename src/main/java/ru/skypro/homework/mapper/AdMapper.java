package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    Ad toDto(AdEntity adEntity);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    ExtendedAd toExtendedDto(AdEntity adEntity);

    List<Ad> toDtoList(List<AdEntity> adEntities);

    default Ads toAdsDto(List<AdEntity> adEntities) {
        Ads ads = new Ads();
        ads.setCount(adEntities.size());
        ads.setResults(toDtoList(adEntities));
        return ads;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd);

    default AdEntity toEntityWithAuthor(CreateOrUpdateAd dto, UserEntity author) {
        AdEntity entity = toEntity(dto);
        entity.setAuthor(author);
        return entity;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateAdFromDto(CreateOrUpdateAd createOrUpdateAd, @MappingTarget AdEntity adEntity);
}