package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.util.UriComponentsBuilder;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.User;

@Mapper
public abstract class UserMapper {

    @Mapping(target = "image", expression = "java(setImageURI())")
    public abstract UserDto toDto(User entity);

    public abstract User toEntity(UpdateUserDto dto);

    public String setImageURI() {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port("8080")
                .pathSegment("users", "me", "image")
                .toUriString();
    }

}
