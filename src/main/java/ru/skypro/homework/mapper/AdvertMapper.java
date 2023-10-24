package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Advert;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    AdDto advertToAdvertDto(Advert advert);

    Advert toAdvert(CreateOrUpdateAdDto createOrUpdateAdDto);

    void updateAdFromDto(CreateOrUpdateAdDto createOrUpdateAdDto, @MappingTarget Advert advert);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    List<AdDto> advertsToAdvertDtos(List<Advert> adverts);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.username", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    ExtendedAdDto toExtendedDto(Advert advert);

}
