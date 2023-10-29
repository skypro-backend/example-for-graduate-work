package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.util.UriComponentsBuilder;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.models.Ad;

@Mapper
public abstract class AdMapper {

    @Mapping(target = "author", source = "entity.user.id")
    @Mapping(target = "image", expression = "java(setImageURI(entity))")
    abstract AdDto toDto(Ad entity);

    abstract Ad toEntity(CreateOrUpdateAdDto dto);

    @Mapping(target = "image", expression = "java(setImageURI(entity))")
    @Mapping(target = "authorFirstName", source = "entity.user.firstName")
    @Mapping(target = "authorLastName", source = "entity.user.lastName")
    @Mapping(target = "email", source = "entity.user.email")
    @Mapping(target = "phone", source = "entity.user.phone")
    abstract ExtendedAdDto toExtendedDto(Ad entity);

    public String setImageURI(Ad source) {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port("8080")
                .pathSegment("ads", source.getPk().toString(), "image")
                .toUriString();
    }

}
