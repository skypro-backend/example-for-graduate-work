package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.user.UpdateUser;
import ru.skypro.homework.user.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "username", target = "email"),
            @Mapping(source = "imageUrl", target = "image"),
            @Mapping(source = "role", target = "role")
    })
    User entityToDto(UserEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "imageUrl", constant = ""),
            @Mapping(source = "role", target = "role")
    })
    UserEntity dtoToEntity(Register dto);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    void updateFromDto(UpdateUser source, @MappingTarget UserEntity dest);

}