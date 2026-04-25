package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "author.id", target = "author")
    @Mapping(expression = "java(\"/ads/\" + adEntity.getPk() + \"/image\")", target = "image")
    Ad toDto(AdEntity adEntity);

    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(expression = "java(\"/users/\" + adEntity.getAuthor().getId() + \"/image\")", target = "authorImage")
    @Mapping(expression = "java(\"/ads/\" + adEntity.getPk() + \"/image\")", target = "image")
    ExtendedAd toExtendedDto(AdEntity adEntity);

    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", ignore = true)
    AdEntity toEntity(Ad ad);
}