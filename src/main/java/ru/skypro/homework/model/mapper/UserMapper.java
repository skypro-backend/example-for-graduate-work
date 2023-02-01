package ru.skypro.homework.model.mapper;

import org.mapstruct.*;
import ru.skypro.homework.model.dto.UserDto;
import ru.skypro.homework.model.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);
    UserDto toDto(User user);

    @BeanMapping (nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(UserDto userDto, @MappingTarget User user);
}