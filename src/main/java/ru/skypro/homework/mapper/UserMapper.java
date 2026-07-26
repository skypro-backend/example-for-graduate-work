package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toDto(ru.skypro.homework.entity.User entity);

    @Mapping(target = "email", source = "username")
    ru.skypro.homework.entity.User toEntity(Register register);

    void updateEntityFromDto(UpdateUser dto, @MappingTarget ru.skypro.homework.entity.User entity);
}
