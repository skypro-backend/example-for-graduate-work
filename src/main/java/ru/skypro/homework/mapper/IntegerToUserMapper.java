package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring")
public interface IntegerToUserMapper {

    // DTO -> Entity
    default User map(Integer id) {

        if (id == null) {
            return null;
        }

        User user = new User();
        user.setId(id);

        return user;
    }

    // Entity -> DTO
    default Integer map(User user) {

        if (user == null) {
            return null;
        }

        return user.getId();
    }
}