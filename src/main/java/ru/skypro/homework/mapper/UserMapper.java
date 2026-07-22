package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Register;
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


    @Mapping(source = "username", target = "email")
    ru.skypro.homework.entity.User registerToEntity(
            Register register
    );


    void updateUserFromDto(
            UpdateUser dto,
            @MappingTarget ru.skypro.homework.entity.User user
    );
}