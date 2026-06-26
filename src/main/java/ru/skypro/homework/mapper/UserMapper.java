package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(User user);

    void updateUserFromDto(UpdateUser dto, @MappingTarget User user);
}