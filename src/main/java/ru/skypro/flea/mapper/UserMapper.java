package ru.skypro.flea.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.flea.dto.RegisterDto;
import ru.skypro.flea.dto.UpdateUserDto;
import ru.skypro.flea.dto.UserDto;
import ru.skypro.flea.model.User;

@Mapper
public interface UserMapper {

    UserDto toUserDto(User entity);

    @Mapping(target = "email", source = "username")
    User createUserFromDto(RegisterDto dto);

    void updateUserFromDto(@MappingTarget User user, UpdateUserDto dto);

}
