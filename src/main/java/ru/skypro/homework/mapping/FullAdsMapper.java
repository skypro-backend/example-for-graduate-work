package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AdsEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface FullAdsMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "authorFirstName")
    @Mapping(target = "lastName", source = "authorLastName")
    User dtoToUserDto(FullAds dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "image", source = "imageId")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "title", source = "title")
    FullAds toDto(AdsEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(source = "authorLastName", target = "author.lastName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "email", target = "author.email")
    @Mapping(source = "image", target = "imageId")
    @Mapping(source = "phone", target = "author.phone")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    AdsEntity toModel (FullAds dto);
}
