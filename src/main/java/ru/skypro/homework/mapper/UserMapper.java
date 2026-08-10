package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "email", source = "username")
    User toUserDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity toEntity(Register register);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserEntity updateUserEntity(UpdateUser updateUser, @MappingTarget UserEntity userEntity);

    default User toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = toUserDto(userEntity);
        // image обрабатывается в сервисе, так как нужен полный URL
        return user;
    }
}