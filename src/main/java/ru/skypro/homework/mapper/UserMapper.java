package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.util.UriComponentsBuilder;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.User;

@Mapper
public abstract class UserMapper {

    @Mapping(target = "image", expression = "java(getImageUri(source))")
    public abstract UserDto convertToDto(User source);

    public abstract User convertToEntity(UpdateUserDto source);

    public String getImageUri(User source) {
        return UriComponentsBuilder
                .newInstance()
                .pathSegment("users", source.getId().toString(), "image")
                .toUriString();
    }

}
