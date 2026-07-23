package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    default ru.skypro.homework.dto.Role map(
            ru.skypro.homework.entity.Role role
    ) {
        if (role == null) {
            return null;
        }

        return ru.skypro.homework.dto.Role.valueOf(role.name());
    }

    default ru.skypro.homework.entity.Role map(
            ru.skypro.homework.dto.Role role
    ) {
        if (role == null) {
            return null;
        }

        return ru.skypro.homework.entity.Role.valueOf(role.name());
    }
}