package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;

@Mapper(componentModel = "spring")
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageMapping")
    @Mapping(target = "pk", source = "id")
    AdsDto adsToDto(Ad ad);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageMapping")
    ExtendedAdDto extendedAdToDto(Ad ad);

    default Ad createOrUpdateAdToAd(CreateOrUpdateAd createOrUpdateAd, Ad ad) {

        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setDescription(createOrUpdateAd.getDescription());

        return ad;

    }

    @Named("imageMapping")
    default String imageMapping(Image image) {

        if (image == null) {
            return null;
        }

        return "/images/" + image.getFileName();

    }

}
