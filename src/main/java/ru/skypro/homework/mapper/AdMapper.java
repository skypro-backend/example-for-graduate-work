package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "images", qualifiedByName = "imagesToUrls")
    @Mapping(target = "pk", source = "id")
    AdDto toAdDto(AdEntity entity);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "images", qualifiedByName = "imagesToUrls")
    ExtendedAdDto toExtendedAdDto(AdEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "images", ignore = true)
    AdEntity toEntity(CreateOrUpdateAdDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "images", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget AdEntity entity, CreateOrUpdateAdDto dto);

    default List<AdDto> toAdDtoList(List<AdEntity> entities) {
        return Optional.ofNullable(entities)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toAdDto)
                .collect(Collectors.toList());
    }

    @Named("imagesToUrls")
    default List<String> imagesToUrls(List<ImageEntity> images) {
        return Optional.ofNullable(images)
                .orElse(Collections.emptyList())
                .stream()
                .map(img -> "/images/" + img.getId())
                .collect(Collectors.toList());
    }
}