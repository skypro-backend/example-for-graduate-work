package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.entity.UserEntity;

/**
 * Interface UserMapper
 * The mapper is used to map the UserDTO fields to the User entity
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(target = "photo", source = "photo")
    UserEntity toEntity(UserDTO dto);

    @Mapping(target = "photo", expression = "java(photoMapper(user))")

    UserDTO toUserDto(UserEntity user);
    default String photoMapper(UserEntity userEntity){
        return "/users/"+ userEntity.getId() + "/photo";
    }
    PhotoEntity map(String value);

    UserEntity toEntity(LoginDTO loginDTO);
    @Mapping(target = "email", source = "username")
    UserEntity toEntity(RegisterDTO registerDTO);

    SecurityUserDto toSecurityDto(UserEntity userEntity);
}
