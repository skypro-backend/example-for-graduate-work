package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

/**
 * Интерфейс маппера для преобразования сущностей {@link AdEntity} в соответствующие DTO и наоборот.
 * Использует библиотеку MapStruct для генерации реализации в Spring-компоненте.
 */
@Mapper(componentModel = "spring")
public interface AdMapper {
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "id", target = "pk")
    Ad toAdDto(AdEntity entity);

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "id", target = "pk")
    ExtendedAd toExtendedAdDto(AdEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    AdEntity toEntity(CreateOrUpdateAd dto);
}