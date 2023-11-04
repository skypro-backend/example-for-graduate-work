package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {

    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
    @Mapping(target = "author", source = "author", qualifiedByName = "authorToInt")
    AdDto toAdDto(Ad ad);

    List<AdDto> toAdsDto(List<Ad> ads);


    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "pk", ignore = true)
    Ad toAdEntity(CreateOrUpdateAdDto createOrUpdateAdDto);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
    ExtendedAdDto toExtendAdDto(Ad ad);

    @Named("imageToPathString")
    default String imageToPathString(Image image) {
        return "/ads/image/" + image.getId();
    }

    @Named("authorToInt")
    default Integer authorToInt(User user) {
        return user.getId();
    }
}