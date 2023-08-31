package ru.skypro.homework.dto.users;

import org.mapstruct.*;
import ru.skypro.homework.entity.users.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);

    User toEntity(UpdateUserDto updateUserDto);

    UserDto toUserDto(User user);

    UpdateUserDto toUpdateUserDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}