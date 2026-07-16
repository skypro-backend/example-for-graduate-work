package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;


@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "image", source = "image", qualifiedByName = "imageToUrl")
    @Mapping(target = "id", source = "id")
    UserDto toDto(UserEntity entity);


    default Optional<UserDto> toDtoOptional(UserEntity entity) {
        return Optional.ofNullable(entity).map(this::toDto);
    }


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "role", expression = "java(dto.getRole() != null ? dto.getRole().name() : \"USER\")")
    @Mapping(target = "email", source = "username")
    UserEntity toEntity(Register dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget UserEntity entity, UpdateUserDto dto);

    @Named("imageToUrl")
    default String imageToUrl(ImageEntity image) {
        return Optional.ofNullable(image)
                .map(img -> "/images/" + img.getId())
                .orElse(null);
    }
}