package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.UpdateUser;

@Mapper(
        componentModel = "spring",
        uses = RoleMapper.class
)
public interface UserMapper {

    ru.skypro.homework.dto.User toDto(
            ru.skypro.homework.entity.User user
    );

    ru.skypro.homework.entity.User toEntity(
            ru.skypro.homework.dto.User user
    );

    void updateUserFromDto(
            UpdateUser dto,
            @MappingTarget ru.skypro.homework.entity.User user
    );

}