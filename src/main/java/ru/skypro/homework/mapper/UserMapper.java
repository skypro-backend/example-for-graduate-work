package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Value;
import ru.skypro.homework.dto.request.UpdateUserRequestDto;
import ru.skypro.homework.dto.response.UserResponseDto;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "username", target = "email")
    UserResponseDto userToDto(User user);

    void updateUserFromDto(UpdateUserRequestDto dto, @MappingTarget User user);
}
