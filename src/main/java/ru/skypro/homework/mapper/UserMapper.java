package ru.skypro.homework.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "email")
    User userEntityToUser(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity userToUserEntity(User user);

    //void fromUserEntityToUser(@MappingTarget UserEntity userEntity, User user);

    //@Mapping(source = "email", target = "username")

   // void fromUserToUserEntity(@MappingTarget User user, UserEntity userEntity); //временно выкл
}
