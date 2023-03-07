package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "regDate", source  = "user.regDate", dateFormat = "dd/MM/yyyy")
    public abstract UserEntity userDtoToEntity(User user);

    @Mapping(target = "id", source = "userEntity.id")
    @Mapping(target = "email", source = "userEntity.email")
    @Mapping(target = "firstName", source = "userEntity.firstName")
    @Mapping(target = "lastName", source = "userEntity.lastName")
    @Mapping(target = "phone", source = "userEntity.phone")
    @Mapping(target = "regDate", source = "userEntity.regDate", dateFormat = "dd/MM/yyyy")
    public abstract User userEntityToDto(UserEntity userEntity);
}
