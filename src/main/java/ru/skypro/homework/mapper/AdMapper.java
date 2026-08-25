package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;

@Mapper(componentModel = "spring")
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    Ad toDto(AdEntity entity);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author.id", source = "author")
    AdEntity toEntity(Ad dto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    ExtendedAd toExtendedDto(AdEntity entity);

    void updateAdFromDto(CreateOrUpdateAd dto, @MappingTarget AdEntity entity);
}