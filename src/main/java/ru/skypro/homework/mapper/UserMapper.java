package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import ru.skypro.homework.dto.UserDto;
import org.mapstruct.Mapper;
import ru.skypro.homework.model.UserModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDto toDTO(UserDto model);

    UserDto toDTO(UserModel model);
    UserDto toModel(UserDto dto);

}
