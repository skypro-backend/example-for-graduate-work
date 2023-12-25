package ru.skypro.homework.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * DTO and Entity fields are identical, no need to set up mapping via annotations
     * @param user UserEntity from DB
     * @return User DTO without password and Ads Collection
     */
    @Mappings({
            @Mapping(source = "login", target = "email"),
            @Mapping(target = "id", expression = "java(user.getId().intValue())")
    })
    User UserEntityToDTO(UserEntity user);

    /**
     *
     * @param user UserDTO
     * @return User entity with null password, login and ads
     */
    @Mapping(source = "email", target = "login")
    UserEntity DtoToEntity(User user);




}