package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    String AVATAR = "/users/avatar/";
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("avatarMapping")
    default String avatarMapping(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return AVATAR + avatar.getId();
    }

    @Mapping(target = "image", source = "avatar", qualifiedByName = "avatarMapping")
    UserDto toDTO(User user);


}
