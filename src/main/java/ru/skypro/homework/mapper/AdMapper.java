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

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "image", expression = "java(getImageUri(source))")
    public abstract AdDto convertToDto(Ad source);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "image", expression = "java(getImageUri(source))")
    public abstract ExtendedAdDto convertToExtendedDto(Ad source);

    public abstract Ad convertToEntity(CreateOrUpdateAdDto source);

    public String getImageUri(Ad source) {
        return UriComponentsBuilder
                .newInstance()
                .pathSegment("ads", source.getId().toString(), "image")
                .toUriString();
    }
}
