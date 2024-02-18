package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AdMapper {
    @Mapping(target = "author",source = "author")
    @Mapping(target = "image",source = "image")
    @Mapping(target = "pk",source = "pk")
    @Mapping(target = "price",source = "price")
    @Mapping(target = "title",source = "title")
    @Mapping(target = "description",source = "description")
    AdEntity toDTO(AdEntity adEntity);

    @Mapping(target = "author",source = "author")
    @Mapping(target = "image",source = "image")
    @Mapping(target = "pk",source = "pk")
    @Mapping(target = "price",source = "price")
    @Mapping(target = "title",source = "title")
    @Mapping(target = "description",source = "description")
    AdEntity toEntity(Ad ad);
}
