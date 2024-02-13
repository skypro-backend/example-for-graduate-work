package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

/**
 * UserMapper
 * <br><i>содержит методы:</i>
 * <br>- toDTO <i>(из {@link UserEntity} в  {@link User})</i>;
 * <br>- toEntity <i>(из {@link User} в  {@link UserEntity})</i>;
 */
@Mapper(componentModel = "spring"
        , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)//
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "userName")//userName в UserEntity, email в User
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "image", source = "image")
    User toDTO(UserEntity userEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userName", source = "email")// email в User, userName в UserEntity
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "image", source = "image")
    UserEntity toEntity(User user);
}
