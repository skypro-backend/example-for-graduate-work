package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

//Как клиент создаёт объект? → DTO → Entity - User

    @Mapping(target = "email", source = "username")
    UserEntity toUser(Register dto);


//Как клиент получает объект? → Entity → DTO - UserEntity

    User toUserDto(UserEntity entity);


//Как клиент обновляет объект? → DTO → Entity (существующий) UpdateUser

    void updateUserFromDto(@MappingTarget UserEntity entity, UpdateUser dto);


}
