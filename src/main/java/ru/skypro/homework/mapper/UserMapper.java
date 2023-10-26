package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.control.MappingControl;
import ru.skypro.homework.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDto toDTO(UserDto model);
    UserDto toModel(UserDto dto);

}
