package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserUpdate;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role",  source = "role")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageMapping")
    UserDto userToDto(User user);

    default User userUpdateToUser(UserUpdate userUpdate, User user) {

        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setPhone(userUpdate.getPhone());

        return user;

    }

    @Named("imageMapping")
    default String imageMapping(Image image) {

        if (image == null) {
            return null;
        }
        return  "/images/" + image.getFileName();

    }

    @Named("roleMapping")
    default String roleMapping(Role role) {
        return role.name();
    }

}
