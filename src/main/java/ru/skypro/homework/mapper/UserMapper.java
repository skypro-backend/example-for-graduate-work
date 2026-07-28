package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toDto(ru.skypro.homework.entity.User entity);

    @Mapping(target = "email", source = "username")
    @Mapping(target = "password", ignore = true)
    ru.skypro.homework.entity.User toEntity(Register register);

    void updateEntityFromDto(UpdateUser dto, @MappingTarget ru.skypro.homework.entity.User entity);
}
