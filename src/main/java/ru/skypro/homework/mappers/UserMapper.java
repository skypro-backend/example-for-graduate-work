package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDTO(UserEntity userEntity);

    UserEntity toModel(User user);
}
