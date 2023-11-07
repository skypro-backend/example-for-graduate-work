package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.entity.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
    default UserDto toUserDto(User user) {
        return null;
    }

    @Mapping(target = "email", source = "username")
    default User toUserEntity(Register register) {
        return null;
    }

    UpdateUserDto toUpdateUserDto(User user);

    @Named("imageToPathString")
    default String imageToPathString(Image image) {
        if (image == null) {
            return null;
        }
        return "/users/image/" + image.getId();
    }
}
