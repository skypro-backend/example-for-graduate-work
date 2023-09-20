package ru.skypro.homework.mappers;

import org.mapstruct.*;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.roles.Role;
import ru.skypro.homework.entity.roles.Roles;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exceptions.NotFoundException;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "roles", qualifiedByName = "mapRoleToSetOfRoles")
    User toEntity(RegisterDto registerDto, @Context CustomUserMapper customUserMapper);

    @Named("mapRoleToSetOfRoles")
    default Set<Roles> mapRoleToSetOfRoles(Role role, @Context CustomUserMapper customUserMapper) {
        return customUserMapper.mapRoleToSetOfRoles(role);
    }

    @Mapping(target = "role", source = "roles", qualifiedByName = "mapSetOfRolesToRole")
    UserDto toUserDto(User user);

    @Named("mapSetOfRolesToRole")
    default Role mapSetOfRolesToRole(Set<Roles> roles) {
        if (roles == null) {
            throw new NotFoundException("Список ролей пуст");
        } else {
            if (roles.stream().anyMatch(role -> role.getRole().equals(Role.ADMIN))) {
                return Role.ADMIN;
            } else {
                return Role.USER;
            }
        }
    }

    UpdateUserDto toUpdateUserDto(User user);

    void updateUser(UpdateUserDto updateUserDto, @MappingTarget User user);

}