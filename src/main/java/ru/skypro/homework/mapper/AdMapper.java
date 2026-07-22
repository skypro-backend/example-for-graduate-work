package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@Mapper(
        componentModel = "spring",
        uses = IntegerToUserMapper.class
)
public interface AdMapper {

    @Mapping(source = "id", target = "pk")
    ru.skypro.homework.dto.Ad toDto(
            ru.skypro.homework.entity.Ad ad
    );

    @Mapping(source = "pk", target = "id")
    ru.skypro.homework.entity.Ad toEntity(
            ru.skypro.homework.dto.Ad ad
    );

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.phone", target = "phone")
    ExtendedAd toExtendedDto(
            ru.skypro.homework.entity.Ad ad
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    ru.skypro.homework.entity.Ad toEntity(
            CreateOrUpdateAd dto
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateEntityFromDto(
            CreateOrUpdateAd dto,
            @MappingTarget ru.skypro.homework.entity.Ad ad
    );
}