package ru.skypro.homework.mappers;

import org.mapstruct.*;
import ru.skypro.homework.dto.usersDTO.*;
import ru.skypro.homework.service.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "email")
    UserEntity toUser(Register register);

    UserDTO toUserDto(UserEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateUserFromUserDto(UserDTO userDto, @MappingTarget UserEntity user);
}
