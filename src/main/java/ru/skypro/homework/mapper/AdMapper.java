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

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "image", expression = "java(setImageURI(entity))")
    abstract AdDto toDto(Ad entity);

    abstract Ad toEntity(CreateOrUpdateAdDto dto);

    @Mapping(target = "image", expression = "java(setImageURI(entity))")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    abstract ExtendedAdDto toExtendedDto(Ad entity);

    public String setImageURI(Ad source) {
        return UriComponentsBuilder.newInstance()
                .pathSegment("ads", source.getPk().toString(), "image")
                .toUriString();
    }

}
