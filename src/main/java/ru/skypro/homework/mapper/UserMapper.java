package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.api.dto.Register;
import ru.skypro.homework.api.dto.Role;
import ru.skypro.homework.api.dto.UpdateUser;
import ru.skypro.homework.api.dto.User;
import ru.skypro.homework.db.model.UserModel;
import ru.skypro.homework.db.model.UserRole;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDto(UserModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "username")
    @Mapping(target = "image", ignore = true)
    UserModel toModel(Register register);

    UpdateUser toUpdateUser(UserModel model);

    void updateModel(UpdateUser updateUser, @MappingTarget UserModel model);

    default Role toDtoRole(UserRole role) {
        return role == null ? null : Role.valueOf(role.name());
    }

    default UserRole toModelRole(Role role) {
        return role == null ? null : UserRole.valueOf(role.name());
    }
}
