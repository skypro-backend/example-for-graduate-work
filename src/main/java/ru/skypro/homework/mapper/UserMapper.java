package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "username", target = "email")
    UserDto userToUserDto(User user);

    @Mapping(target = "password", ignore = true)
    void update(RegisterDto registerDto, @MappingTarget User user);

    void update(UpdateUserDto updateUserDto, @MappingTarget User user);

}
