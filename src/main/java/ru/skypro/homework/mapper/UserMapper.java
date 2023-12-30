package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    String address = "/users/image/";

    @Mapping(target = "image", source = "image", qualifiedByName = "imageToString")
    UserDTO toDto(User entity);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserDTO dto);

    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "username", target = "email")
    User toEntity(Register dto);

    @Named("imageToString")
    default String imageToString(Image image) {
        if (image == null) {
            return null;
        }
        return address + image.getId();
    }
}

