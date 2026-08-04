package ru.skypro.homework.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.data.dto.ads.AdItemDto;
import ru.skypro.homework.data.dto.ads.ExtendedAdItem;
import ru.skypro.homework.data.entity.AdEntity;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AdMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "profile.id")
    @Mapping(target = "image", source = "imageS3Key")
    AdItemDto toAdItemDto(AdEntity entity);

    List<AdItemDto> toAdItemDtoList(List<AdEntity> entities);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "profile.firstName")
    @Mapping(target = "authorLastName", source = "profile.lastName")
    @Mapping(target = "phone", source = "profile.phone")
    @Mapping(target = "email", source = "profile.email")
    @Mapping(target = "image", source = "imageS3Key")
    ExtendedAdItem toExtendedAdItem(AdEntity entity);
}
